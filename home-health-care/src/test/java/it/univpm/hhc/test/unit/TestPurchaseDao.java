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


import it.univpm.hhc.model.dao.PurchaseDao;
import it.univpm.hhc.model.dao.PurchaseDaoDefault;
import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.dao.UserDetailsDaoDefault;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.test.DataServiceConfigTest;

public class TestPurchaseDao {
	
	private AnnotationConfigApplicationContext ctx;
	private PurchaseDao purchaseDao;
	private UserDetailsDao userDetailsDao;
	private SessionFactory sf;
	private User u;
	private Address a;

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
		 * Ciascun test ha bisogno di un contesto applicativo e un purchaseDao
		 */
		System.out.println("Prepare the test case environment");

		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);
		
		purchaseDao = ctx.getBean("purchaseDao", PurchaseDao.class);
		
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

		purchaseDao.setSession(s);

		assertEquals(s, purchaseDao.getSession()); // s.equals(purchaseDao.getSession());
		assertSame(s, purchaseDao.getSession()); // s == purchaseDao.getSession();
		assertTrue(purchaseDao.getSession().getTransaction().isActive());

		s.getTransaction().commit();

		assertFalse(purchaseDao.getSession().getTransaction().isActive());

	}
	
	@Test
	void testAutoCreationOfSession() {
		/**
		 * Check that the dao opens a new session if no session is passed
		 * (NB the returned session has transaction disabled by default)
		 * 		 
		 */
		
		Session s = purchaseDao.getSession();
		assertNotNull(s); // s.equals(purchaseDao.getSession());
		assertFalse(s.getTransaction().isActive());
	}
	
	/*@Test
	void testCreatepurchaseDuplicateId() {
		/**
		 * We test that it is possible to create two purchases with same id
		 
		
		Session s = sf.openSession();

		purchaseDao.setSession(s);

		Purchase newpurchase1 = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, a);

		try {
			Purchase newpurchase2 = purchaseDao.create(newpurchase1.getPay_method(), newpurchase1.getDate(), newpurchase1.getTotal(), u, a);
			newpurchase2.setPurchase_id(newpurchase1.getPurchase_id());
			assertTrue(false); //non dovrebbe poter essere possibile
		} catch (Exception e) {
			// pass
			fail("Unexpected exception creating purchase with duplicate name: " + e.getMessage());
		}

	}*/

	@Test
	void testNopurchasesAtBeginning() {
		/**
		 * Check that there are no purchase when the application loads
		 */

		Session s = sf.openSession();

		purchaseDao.setSession(s);

		List<Purchase> allpurchases = purchaseDao.findAll();

		assertEquals(allpurchases.size(), 0);
	}

	@Test
	void testAllCreatedAreFound() {
		/**
		 * Generate N purchase, find all of them
		 */
		int N = 10;

		Session s = sf.openSession();

		purchaseDao.setSession(s);

		for (int i = 0; i < N; i++) {
			purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, a);

			List<Purchase> allpurchases = purchaseDao.findAll();
			assertEquals(allpurchases.size(), i + 1);
		}
	}
	
	@Test
	void FindPurchaseById() {
		
		Session s = sf.openSession();

		Purchase newPurchase = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, a);
		Long Id = newPurchase.getPurchase_id();
		try {			
			assertSame(newPurchase.getPurchase_id(), Id);
			}catch (Exception e) {		 
			fail("No purchase found for the given id: " + e.getMessage());
			
		}
		
	}
	
	@Test
	void FindPurchaseByUserId() {
		
		Session s = sf.openSession();

		Purchase newPurchase = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, a);
		User user = newPurchase.getUser();
		try {			
			assertSame(newPurchase.getUser(), user);
			}catch (Exception e) {		 
			fail("No purchase found for the given user: " + e.getMessage());
			
		}
		
	}
	
	@Test
	void testpurchaseCanHaveNoAddress() {
		/**
		 * An purchase can have empty user field
		 */
		Session s = sf.openSession();

		Purchase newpurchase = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, null);

		assertNotNull(newpurchase);
	}
	
	

	@Test
	void testDeleteAPurchase() {
		
		Session s = sf.openSession();

		purchaseDao.setSession(s);
		
		Purchase purchase = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, a);
		
		try {
			purchaseDao.delete(purchase);
			assertTrue(true);
		} catch (Exception e) {
			fail("An attempt to delete an existing purchase failed");
		}

	}
	
	
	@Test
	void testpurchaseIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		purchaseDao.setSession(s);

		Purchase inserted = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, u, a);
		
		Purchase updated = new Purchase();
		updated.setPurchase_id(inserted.getPurchase_id());
		updated.setPay_method("visa");
		updated.setDate(inserted.getDate());
		updated.setTotal(21.3);
		updated.setAddress(a);
		updated.setUser(u);
		
		updated = purchaseDao.update(updated);
		
		Purchase found = purchaseDao.findById(inserted.getPurchase_id());
		
		assertSame(inserted, updated);
		assertSame(updated, found);
		assertSame(found, inserted);
	}
	
	@Test
	void testpurchaseCanHaveNoPayMethod() {
		/**
		 * An purchase can have empty civ_num field*/
		 
		Session s = sf.openSession();

		Purchase newpurchase = purchaseDao.create("", java.time.LocalDate.now(), 21.4, u, a);

		assertNotNull(newpurchase);
	}
	
	@Test
	void testpurchaseCanHaveNoDate() {
		/**
		 * An purchase can have empty civ_num field*/
		 
		Session s = sf.openSession();

		Purchase newpurchase = purchaseDao.create("visa", null, 21.4, u, a);

		assertNotNull(newpurchase);
	}
	
	@Test
	void testpurchaseCanHaveNoTotalSetToZero() {
		/**
		 * An purchase can have empty user field
		 */
		Session s = sf.openSession();

		Purchase newpurchase = purchaseDao.create("visa", java.time.LocalDate.now(), 0, u, a);

		assertNotNull(newpurchase);
	}
	

	
	
	@Test
	void testpurchaseCanHaveNoUser() {
		/**
		 * An purchase can have empty civ_num field*/
		 
		Session s = sf.openSession();

		Purchase newpurchase = purchaseDao.create("visa", java.time.LocalDate.now(), 21.4, null, a);

		assertNotNull(newpurchase);
	}
	

}
