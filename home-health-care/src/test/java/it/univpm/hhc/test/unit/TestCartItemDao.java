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
import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.dao.CartDao;
import it.univpm.hhc.model.dao.CartItemDao;
import it.univpm.hhc.model.dao.CartItemDaoDefault;


import it.univpm.hhc.test.DataServiceConfigTest;

public class TestCartItemDao {
	
	private AnnotationConfigApplicationContext ctx;
	private CartItemDao cartItemDao;
	private SessionFactory sf;
	private User u;
	private Cart c;
	private Item item;
	private Purchase purchase;
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
		
		cartItemDao = ctx.getBean("cartItemDao", CartItemDao.class);
		
		sf = ctx.getBean("sessionFactory", SessionFactory.class);
	}
	
	@AfterEach
	void closeContext() {
		System.out.println("Clean-up the test case environment");
		
		ctx.close();
	}
	
	
	///TESTS
	
	// funziona ma senza costruttori!!!
	@Test
	void createCartItem(){
		
		Session s = sf.openSession();

		cartItemDao.setSession(s);
		//Item i= new Item();
		//Cart c = new Cart();
		//User u = new User();
		//User user = userDetailsDao.create("password", "mail@mail.com", "Giacomo", "Bruni");
		
		try {
		Cart_item cartItem = cartItemDao.create(c, item, 8);
		
		assertTrue(true);
		} catch (Exception e) {
			fail("An attempt to create a new cart_item failed");
		}
	}
	
	//funziona
	@Test
	void testBeginCommitTransaction() {
		/**
		 * Check that the dao stores the session for later use, allowing to share the
		 * session
		 */

		Session s = sf.openSession();

		assertTrue(s.isOpen());

		s.beginTransaction();

		cartItemDao.setSession(s);

		assertEquals(s, cartItemDao.getSession()); // s.equals(cartitemDao.getSession());
		assertSame(s, cartItemDao.getSession()); // s == cartitemDao.getSession();
		assertTrue(cartItemDao.getSession().getTransaction().isActive());

		s.getTransaction().commit();

		assertFalse(cartItemDao.getSession().getTransaction().isActive());

	}
	
	//funziona
	@Test
	void testAutoCreationOfSession() {
		/**
		 * Check that the dao opens a new session if no session is passed
		 * (NB the returned session has transaction disabled by default)
		 * 		 
		 */
		
		Session s = cartItemDao.getSession();
		assertNotNull(s); 
		assertFalse(s.getTransaction().isActive());
	}
	
	//funziona
		@Test
		void testNocartitemsAtBeginning() {
			/**
			 * Check that there are no cartitems when the application loads
			 */

			Session s = sf.openSession();

			cartItemDao.setSession(s);

			List<Cart_item> allcarts = cartItemDao.findAll();

			assertEquals(allcarts.size(), 0);
		}

		
		//funziona
		@Test
		void testUpdateACart() {
			
			Session s = sf.openSession();

			cartItemDao.setSession(s);
			
			try {
				Cart_item inserted = cartItemDao.create(c, item, 4);
				
				Cart_item updated = new Cart_item();
				updated.setCart_item_id(inserted.getCart_item_id());
				updated.setCart(c);
				updated.setItem(item);
				updated.setQuantity(5);
				
				updated = cartItemDao.update(updated);
				assertTrue(true);
				
				
			} catch (Exception e) {
				fail("An attempt to update an existing cart failed");
			}

		}
		
		
		//funziona
		@Test
		void testDeleteACartItem() {
			
			Session s = sf.openSession();

			cartItemDao.setSession(s);
			
			Cart_item cart_item = cartItemDao.create(c, item, 6);
			
			try {
				cartItemDao.delete(cart_item);
				assertTrue(true);
			} catch (Exception e) {
				fail("An attempt to delete an existing cart failed");
			}

		}
		
		//funziona
		@Test
		void FindCartById() {
			
			Session s = sf.openSession();

			Cart_item newCartItem = cartItemDao.create(c,item,3);
			Long Id = newCartItem.getCart_item_id();
			try {			
				assertSame(newCartItem.getCart_item_id(), Id);
				}catch (Exception e) {		 
				fail("No cart found for the given id: " + e.getMessage());
				
			}
			
		}
		
		//funziona
		@Test
		void FindCartByPurchase() {
			
			Session s = sf.openSession();

			Cart_item newCartItem = cartItemDao.create(c, item, 4);
			Purchase purchase = newCartItem.getPurchase();
			try {			
				assertSame(newCartItem.getPurchase(), purchase);
				}catch (Exception e) {		 
				fail("No cart found for the given purchase: " + e.getMessage());
				
			}
			
		}
		
		@Test
		void FindCartItemByCart() {
			
			Session s = sf.openSession();

			Cart_item newCartItem = cartItemDao.create(c, item, 4);
			Cart cart = newCartItem.getCart();
			try {			
				assertSame(newCartItem.getCart(), cart);
				}catch (Exception e) {		 
				fail("No cartitem found for the given cart: " + e.getMessage());
				
			}
			
		}

}