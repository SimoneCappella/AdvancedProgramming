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

import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.dao.UserDetailsDaoDefault;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.dao.CartDao;
import it.univpm.hhc.model.dao.CartDaoDefault;


import it.univpm.hhc.test.DataServiceConfigTest;

public class TestCartDao {
	
	private AnnotationConfigApplicationContext ctx;
	private CartDao cartDao;
	private SessionFactory sf;
	private User u;
	private UserDetailsDao userDetailsDao;

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
		
		cartDao = ctx.getBean("cartDao", CartDao.class);
		
		sf = ctx.getBean("sessionFactory", SessionFactory.class);
	}
	
	@AfterEach
	void closeContext() {
		System.out.println("Clean-up the test case environment");
		
		ctx.close();
	}
	
	
	///TESTS
	
	@Test
	void createCart(){
		
		Session s = sf.openSession();

		cartDao.setSession(s);
		
		try {
		Cart cart = cartDao.create(4, 20.2, u);
		
		assertTrue(true);
		} catch (Exception e) {
			fail("An attempt to create a new cart failed");
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

		cartDao.setSession(s);

		assertEquals(s, cartDao.getSession()); // s.equals(cartDao.getSession());
		assertSame(s, cartDao.getSession()); // s == cartDao.getSession();
		assertTrue(cartDao.getSession().getTransaction().isActive());

		s.getTransaction().commit();

		assertFalse(cartDao.getSession().getTransaction().isActive());

	}
	
	
	@Test
	void testAutoCreationOfSession() {
		/**
		 * Check that the dao opens a new session if no session is passed
		 * (NB the returned session has transaction disabled by default)
		 * 		 
		 */
		
		Session s = cartDao.getSession();
		assertNotNull(s); // s.equals(subDao.getSession());
		assertFalse(s.getTransaction().isActive());
	}

	
	@Test
	void testNocartsAtBeginning() {
		/**
		 * Check that there are no carts when the application loads
		 */

		Session s = sf.openSession();

		cartDao.setSession(s);

		List<Cart> allcarts = cartDao.findAll();

		assertEquals(allcarts.size(), 0);
	}

	
	@Test
	void testAllCreatedAreFound() {
		/**
		 * Generate N carts, find all of them
		 */
		int N = 10;

		Session s = sf.openSession();

		cartDao.setSession(s);

		for (int i = 0; i < N; i++) {
			cartDao.create(4, 20.5, u);

			List<Cart> allcarts = cartDao.findAll();
			assertEquals(allcarts.size(), i + 1);
		}
	}
	
	
	@Test
	void testUpdateACart() {
		
		Session s = sf.openSession();

		cartDao.setSession(s);
		
		try {
			Cart inserted = cartDao.create(4, 20.5, u);
			
			Cart updated = new Cart();
			updated.setCart_id(inserted.getCart_id());
			updated.setItem_num(5);
			updated.setTotal(30.0);
			updated.setUser(u);
			
			updated = cartDao.update(updated);
			assertTrue(true);
			
			
		} catch (Exception e) {
			fail("An attempt to update an existing cart failed");
		}

	}
	
	
	@Test
	void testsubIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		cartDao.setSession(s);

		Cart inserted = cartDao.create(4, 20.5, u);
		
		Cart updated = new Cart();
		updated.setCart_id(inserted.getCart_id());
		updated.setItem_num(5);
		updated.setTotal(30.0);
		updated.setUser(u);
		
		updated = cartDao.update(updated);
		
		Cart found = cartDao.findById(inserted.getCart_id());
		
		assertSame(inserted, updated);
		assertSame(updated, found);
		assertSame(found, inserted);
	}
	
	
	@Test
	void testDeleteACart() {
		
		Session s = sf.openSession();

		cartDao.setSession(s);
		
		Cart cart = cartDao.create(6, 30.5, u);
		
		try {
			cartDao.delete(cart);
			assertTrue(true);
		} catch (Exception e) {
			fail("An attempt to delete an existing cart failed");
		}

	}
	
	
	@Test
	void FindCartById() {
		
		Session s = sf.openSession();

		Cart newCart = cartDao.create(2,5.00,u);
		Long Id = newCart.getCart_id();
		try {			
			assertSame(newCart.getCart_id(), Id);
			}catch (Exception e) {		 
			fail("No cart found for the given id: " + e.getMessage());
			
		}
		
	}
	
	
	@Test
	void FindCartByUserId() {
		
		Session s = sf.openSession();

		Cart newCart = cartDao.create(2,4.6,u);
		User user = newCart.getUser();
		try {			
			assertSame(newCart.getUser(), user);
			}catch (Exception e) {		 
			fail("No cart found for the given id: " + e.getMessage());
			
		}
		
	}
}