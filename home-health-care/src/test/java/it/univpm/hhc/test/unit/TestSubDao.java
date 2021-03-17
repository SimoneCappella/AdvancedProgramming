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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import it.univpm.advprog.users.model.entities.User;

import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.dao.UserDetailsDaoDefault;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.dao.SubDao;
import it.univpm.hhc.model.dao.SubDaoDefault;


import it.univpm.hhc.test.DataServiceConfigTest;



public class TestSubDao {
	
	private AnnotationConfigApplicationContext ctx;
	private SubDao subDao;
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
		 * Ciascun test ha bisogno di un contesto applicativo e un UserDetailsDao
		 */
		System.out.println("Prepare the test case environment");

		ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class);
		
		subDao = ctx.getBean("subDao", SubDao.class);
		
		sf = ctx.getBean("sessionFactory", SessionFactory.class);
	}
	
	@AfterEach
	void closeContext() {
		System.out.println("Clean-up the test case environment");
		
		ctx.close();
	}
	
	
	///TESTS
	
	
	@Test
	void createSub(){
		
		Session s = sf.openSession();

		subDao.setSession(s);
		
		try {
		Sub sub = subDao.create("Premium", 20.2, 5);
		assertTrue(true);
		} catch (Exception e) {
			fail("An attempt to create a new sub failed");
		}
	}
	
	@Test
	void testBeginCommitTransaction() {
		/**
		 * Check that the dao stores the session for later use, allowing to share the
		 * session
		 */

		Session s = sf.openSession();

		assertTrue(s.isOpen());

		s.beginTransaction();

		subDao.setSession(s);

		assertEquals(s, subDao.getSession()); // s.equals(subDao.getSession());
		assertSame(s, subDao.getSession()); // s == subDao.getSession();
		assertTrue(subDao.getSession().getTransaction().isActive());

		s.getTransaction().commit();

		assertFalse(subDao.getSession().getTransaction().isActive());

	}
	
	@Test
	void testAutoCreationOfSession() {
		/**
		 * Check that the dao opens a new session if no session is passed
		 * (NB the returned session has transaction disabled by default)
		 * 		 
		 */
		
		Session s = subDao.getSession();
		assertNotNull(s); // s.equals(subDao.getSession());
		assertFalse(s.getTransaction().isActive());
	}
	
	@Test
	void testCreateSubDuplicateNames() {
		/**
		 * We test that it is possible to create two subs with same name and surname
		 */
		
		Session s = sf.openSession();

		subDao.setSession(s);

		Sub newsub1 = subDao.create("Premium", 20.2, 5);

		try {
			Sub newsub2 = subDao.create(newsub1.getName(), newsub1.getPrice(), newsub1.getDiscount());
			assertTrue(true);
		} catch (Exception e) {
			// pass
			fail("Unexpected exception creating an sub with duplicate name: " + e.getMessage());
		}

	}

	@Test
	void testNosubsAtBeginning() {
		/**
		 * Check that there are no sub when the application loads
		 */

		Session s = sf.openSession();

		subDao.setSession(s);

		List<Sub> allsubs = subDao.findAll();

		assertEquals(allsubs.size(), 0);
	}

	@Test
	void testAllCreatedAreFound() {
		/**
		 * Generate N sub, find all of them
		 */
		int N = 10;

		Session s = sf.openSession();

		subDao.setSession(s);

		for (int i = 0; i < N; i++) {
			subDao.create("Premium", 20.2, 5);

			List<Sub> allsubs = subDao.findAll();
			assertEquals(allsubs.size(), i + 1);
		}
	}
	
	@Test
	void testUpdateAnSub() {
		
		Session s = sf.openSession();

		subDao.setSession(s);
		
		try {
			Sub inserted = subDao.create("Premium", 20.2, 5);
			
			Sub updated = new Sub();
			updated.setSub_id(inserted.getSub_id());
			updated.setName("Gold");
			updated.setPrice(10.1);
			updated.setDiscount(3);
			
			updated = subDao.update(updated);
			assertTrue(true);
			
			
		} catch (Exception e) {
			fail("An attempt to update an existing sub failed");
		}

	}
	
	@Test
	void testsubIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		subDao.setSession(s);

		Sub inserted = subDao.create("Premium", 20.2, 5);
		
		Sub updated = new Sub();
		updated.setSub_id(inserted.getSub_id());
		updated.setName("Gold");
		updated.setPrice(10.1);
		updated.setDiscount(3);
		
		updated = subDao.update(updated);
		
		Sub found = subDao.findById(inserted.getSub_id());
		
		assertSame(inserted, updated);
		assertSame(updated, found);
		assertSame(found, inserted);
	}
	
	@Test
	void testsubCanHaveNoName() {
		//An sub can have empty name field
		 
		Session s = sf.openSession();

		Sub newsub = subDao.create("", 20.2, 5);

		assertNotNull(newsub);
	}
	
	@Test
	void testsubCanHaveNoPrice() {
		/**
		 * An sub can have empty price field*/
		 
		Session s = sf.openSession();

		Sub newsub = subDao.create("Premium", 0.0, 5);

		assertNotNull(newsub);
	}
	
	@Test
	void testsubCanHaveNoDiscount() {
		/**
		 * An sub can have empty discount field
		 */
		Session s = sf.openSession();

		Sub newsub = subDao.create("Premium", 20.2, 0);

		assertNotNull(newsub);
	}
	
	
	@Test
	void testDeleteASub() {
		
		Session s = sf.openSession();

		subDao.setSession(s);
		
		Sub sub = subDao.create("Premium", 20.2, 5);
		
		try {
			subDao.delete(sub);
			assertTrue(true);
		} catch (Exception e) {
			fail("An attempt to delete an existing sub failed");
		}

	}
	
	
	@Test
	void testDeleteNonExistingSubDoesNotCauseError() {
		/**
		 * An sub that does not exist can be deleted without notice to the callee
		 * */
		 
		Session s = sf.openSession();

		subDao.setSession(s);
				
		Sub fake = new Sub();
		fake.setSub_id(53L);
		
		assertNull(subDao.findById(fake.getSub_id()));
		
		try {
			subDao.delete(fake);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}

}
