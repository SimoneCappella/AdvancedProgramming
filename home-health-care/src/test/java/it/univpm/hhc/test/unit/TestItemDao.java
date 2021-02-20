package it.univpm.hhc.test.unit;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import it.univpm.advprog.items.model.entities.Item;
import it.univpm.hhc.model.dao.ItemDao;
import it.univpm.hhc.model.dao.ItemDaoDefault;
import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.dao.UserDetailsDaoDefault;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.test.DataServiceConfigTest;

public class TestItemDao {

	private AnnotationConfigApplicationContext ctx;
	private ItemDao itemDao;
	private SessionFactory sf;

	@BeforeAll
	static void setup() {
		System.out.println("Prepare the test suite environment");
		// TODO prepare data structure and storage, if needed

	}

	@AfterAll
	static void tearDown() {
		System.out.println("Clean-up the test suite environment");
		// TODO cleanup data structures and storage, if needed

	}
	
	@BeforeEach
	void openContext() {
		/**
		 * Ciascun test ha bisogno di un contesto applicativo e un itemDao
		 */
		System.out.println("Prepare the test case environment");

		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);
		
		itemDao = ctx.getBean("itemDao", ItemDao.class);
		
		sf = ctx.getBean("sessionFactory", SessionFactory.class);
	}
	
	@AfterEach
	void closeContext() {
		System.out.println("Clean-up the test case environment");
		
		ctx.close();
	}
	
	
	///TESTS
	
	
	@Test
	void testBeginCommitTransaction() {
		/**
		 * Check that the dao stores the session for later use, allowing to share the
		 * session
		 */

		Session s = sf.openSession();

		assertTrue(s.isOpen());

		s.beginTransaction();

		itemDao.setSession(s);

		assertEquals(s, itemDao.getSession()); // s.equals(itemDao.getSession());
		assertSame(s, itemDao.getSession()); // s == itemDao.getSession();
		assertTrue(itemDao.getSession().getTransaction().isActive());

		s.getTransaction().commit();

		assertFalse(itemDao.getSession().getTransaction().isActive());

	}

	@Test
	void testAutoCreationOfSession() {
		/**
		 * Check that the dao opens a new session if no session is passed
		 * (NB the returned session has transaction disabled by default)
		 * 		 
		 */
		
		Session s = itemDao.getSession();
		assertNotNull(s); // s.equals(itemDao.getSession());
		assertFalse(s.getTransaction().isActive());
	}



	@Test
	void testCreateNoDuplicateItems() {
		/**
		 * We test that it is possible to create two items with same name and surname
		 */
		
		Session s = sf.openSession();

		itemDao.setSession(s);

		Item newItem1 = itemDao.create("Titolo", "Description", 5.2, "Image");

		try {
			Item newItem2 = itemDao.create(newItem1.getTitle(), newItem1.getDescription(), newItem1.getPrice(), newItem1.getImage());
			assertTrue(false);
		} catch (Exception e) {
			// pass
			fail("An attempt to create an item with the same parameters as an already existing one failed: " + e.getMessage());
		}

	}

	@Test
	void testNoItemsAtBeginning() {
		/**
		 * Check that there are no items when the application loads
		 */

		Session s = sf.openSession();

		itemDao.setSession(s);

		List<Item> allItems = itemDao.findAll();

		assertEquals(allItems.size(), 0);
	}

	@Test
	void testAllCreatedAreFound() {
		/**
		 * Generate N items, find all of them
		 */
		int N = 10;

		Session s = sf.openSession();

		itemDao.setSession(s);

		for (int i = 0; i < N; i++) {
			itemDao.create("Titolo", "Description", 5.2, "Image");

			List<Item> allItems = itemDao.findAll();
			assertEquals(allItems.size(), i + 1);
		}
	}
	
	@Test
	void testItemCanHaveNoImage() {
		/**
		 * An item can have empty image field
		 */
		Session s = sf.openSession();

		Item newItem = itemDao.create("Titolo", "Description", 5.2, null);

		assertNotNull(newItem);
	}
	
	@Test
	void testItemIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		itemDao.setSession(s);

		Item inserted = itemDao.create("Titolo", "Description", 5.2, "Image");
		
		Item updated = new Item();
		updated.setItem_id(inserted.getItem_id());
		updated.setTitle("Title1");
		updated.setDescription("Description1");
		updated.setPrice(8.0);
		updated.setImage("Image1");
		
		itemDao.update(updated);
		
		Item found = itemDao.findById(inserted.getItem_id());
		
		assertSame(inserted, updated);
		assertSame(updated, found);
		assertSame(found, inserted);
	}
	
	/*@Test
	void testDeleteNonExistingItemDoesNotCauseError() {
		/**
		 * An item that does not exist can be deleted without notice to the callee
		 * 
		 
		Session s = sf.openSession();

		itemDao.setSession(s);
				
		Item fake = new Item();
		fake.setItem_id(53L);
		
		assertNull(itemDao.findById(fake.getItem_id()));
		
		try {
			itemDao.delete(fake);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception when deleting fake item");
		}
		
	}
	
	@Test
	void testNormalUserCanNeitherCreateNorEditAnExistingItem() {
		Session s = sf.openSession();
		
		//User creation
		UserDetailsDao userDao = ctx.getBean("userDao", UserDetailsDao.class);
		userDao.setSession(s);
		User user = userDao.create("password", "email", "name", "surname"); //when created, a user is not an admin by design
		
		//Item creation
		itemDao.setSession(s);
		
		Item item = new Item();
		item.setTitle("titolo");
		item.setDescription("desc");
		item.setPrice(5.1);
		item.setImage("path");
		
		//User login
		//....
		
		//User attempt to Add a new Item to the Catalogue
		//...
		
		//User attempt to Edit an existing Item of the Catalogue
		//...
	}*/
}
