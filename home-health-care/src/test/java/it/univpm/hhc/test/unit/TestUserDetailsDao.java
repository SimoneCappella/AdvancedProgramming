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
import java.util.StringTokenizer;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.test.DataServiceConfigTest;

public class TestUserDetailsDao {

	private AnnotationConfigApplicationContext ctx;
	private UserDetailsDao UserDetailsDao;
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
		
		UserDetailsDao = ctx.getBean("userDetailsDao", UserDetailsDao.class);
		
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

		UserDetailsDao.setSession(s);

		assertEquals(s, UserDetailsDao.getSession()); // s.equals(UserDetailsDao.getSession());
		assertSame(s, UserDetailsDao.getSession()); // s == UserDetailsDao.getSession();
		assertTrue(UserDetailsDao.getSession().getTransaction().isActive());

		s.getTransaction().commit();

		assertFalse(UserDetailsDao.getSession().getTransaction().isActive());

	}

	@Test
	void testAutoCreationOfSession() {
		/**
		 * Check that the dao opens a new session if no session is passed
		 * (NB the returned session has transaction disabled by default)
		 * 		 
		 */
		
		Session s = UserDetailsDao.getSession();
		assertNotNull(s); // s.equals(UserDetailsDao.getSession());
		assertFalse(s.getTransaction().isActive());
	}



	@Test
	void testCreateUserDuplicateNames() {
		/**
		 * We test that it is possible to create two users with same name and surname
		 */
		
		Session s = sf.openSession();

		UserDetailsDao.setSession(s);

		User newUser1 = UserDetailsDao.create("Password","E-mail","Name","Surname");

		try {
			User newUser2 = UserDetailsDao.create(newUser1.getName(), newUser1.getSurname(), newUser1.getEmail(), newUser1.getPassword());
			assertTrue(true);
		} catch (Exception e) {
			// pass
			fail("Unexpected exception creating user with duplicate name: " + e.getMessage());
		}

	}

	@Test
	void testNoUsersAtBeginning() {
		/**
		 * Check that there are no users when the application loads
		 */

		Session s = sf.openSession();

		UserDetailsDao.setSession(s);

		List<User> allUsers = UserDetailsDao.findAll();

		assertEquals(allUsers.size(), 0);
	}

	@Test
	void testAllCreatedAreFound() {
		/**
		 * Generate N users, find all of them
		 */
		int N = 10;

		Session s = sf.openSession();

		UserDetailsDao.setSession(s);

		for (int i = 0; i < N; i++) {
			UserDetailsDao.create("Password","E-mail","Name","Surname");

			List<User> allUsers = UserDetailsDao.findAll();
			assertEquals(allUsers.size(), i + 1);
		}
	}
	
	
	public static boolean isValidEmailAddress(String email)
		{
		  boolean result = true;
		  try {
		    InternetAddress emailAddr = new InternetAddress(email);
		    emailAddr.validate();
		  } catch (AddressException ex) {
		    result = false;
		  }
		  return result;
		}
	
	
	
	@Test
	void testUserCanEnterInvalidEmail() {
		
		Session s = sf.openSession();
		UserDetailsDao.setSession(s);
		
		
		boolean r = isValidEmailAddress(UserDetailsDao.create("Password","mailmail.com","Nameasd","Surnameasd").getEmail());
		assertSame(false,r);
				
	}

}
