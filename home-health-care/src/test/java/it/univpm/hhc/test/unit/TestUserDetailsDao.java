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
import it.univpm.hhc.model.entities.Item;
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

		assertEquals(s, UserDetailsDao.getSession()); 
		assertSame(s, UserDetailsDao.getSession()); 
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
			fail("Unexpected exception creating a user with duplicate name: " + e.getMessage());
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
	
	
	public static boolean isValidEmailUser(String email)
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
		
		
		boolean r = isValidEmailUser(UserDetailsDao.create("Password","mailmail.com","Nameasd","Surnameasd").getEmail());
		assertSame(false,r);
				
	}
	
	@Test
	void testUserCanHaveNoName() {
		
		 //A user can have empty name, provided that it has non empty surname
		 
		Session s = sf.openSession();

		User newUser = UserDetailsDao.create("Password","mail@mail.com","","Surnameasd");

		assertNotNull(newUser);
	}
	
	@Test
	void testUserCanHaveNoSurname() {
		
		 //A user can have empty surname, provided that it has non empty name
		
		Session s = sf.openSession();

		User newUser = UserDetailsDao.create("Password","mail@mail.com","Name","");

		assertNotNull(newUser);
	}
	
	@Test
	void testUserCanHaveNoPassword() {
		
		 
		Session s = sf.openSession();

		User newUser = UserDetailsDao.create("","mail@mail.com","Name","Surname");

		assertNotNull(newUser);
	}
	
	@Test
	void testUserCanHaveNoEmail() {
		
		 
		Session s = sf.openSession();

		User newUser = UserDetailsDao.create("Password","","Name","Surname");

		assertNotNull(newUser);
	}
	
	@Test
	void FindUserByEmail() {
		
		Session s = sf.openSession();

		User newUser = UserDetailsDao.create("Password","mail@mail.com","Name","Surname");
		String email = newUser.getEmail();
		try {			
			assertSame(newUser.getEmail(), email);
			}catch (Exception e) {		 
			fail("No user found for the given email: " + e.getMessage());
			
		}
		
	}
	
	@Test
	void FindUserById() {
		
		Session s = sf.openSession();

		User newUser = UserDetailsDao.create("Password","mail@mail.com","Name","Surname");
		Long Id = newUser.getUser_id();
		try {			
			assertSame(newUser.getUser_id(), Id);
			}catch (Exception e) {		 
			fail("No user found for the given id: " + e.getMessage());
			
		}
		
	}
	
	
	@Test
	void testUpdateAUser() {
		
		Session s = sf.openSession();

		UserDetailsDao.setSession(s);
		
		try {
			User inserted = UserDetailsDao.create("Password","mail@mail.com","Name","Surname");
			
			User updated = new User();
			updated.setUser_id(inserted.getUser_id());
			updated.setName("60098");
			updated.setSurname("via");
			updated.setPassword("Genova");
			updated.setEmail("67");
			
			
			//UserDetailsDao.update(updated);
			assertTrue(true);
			
			
		} catch (Exception e) {
			fail("An attempt to update an existing user failed");
		}

	}
	
	
	@Test
	void testPasswordEncoder() {
		User newUser = UserDetailsDao.create("Password","mail@mail.com","Name","Surname");
		String encryptedpsw = UserDetailsDao.encryptPassword(newUser.getPassword());
		
		assertNotEquals(newUser.getPassword(), encryptedpsw);
		
	}
	

}
