package it.univpm.hhc.test;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import it.univpm.hhc.model.dao.ProvaDao;
import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.entities.Prova;
import it.univpm.hhc.model.entities.User;

public class LoadDataTest {

	public static void main(String ...args) {
		
		
//		logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			
			ProvaDao provaDao = ctx.getBean("provaDao", ProvaDao.class);
			
			UserDetailsDao userDao = ctx.getBean(UserDetailsDao.class);
			
			userDao.setPasswordEncoder(new BCryptPasswordEncoder());
			
			try (Session session = sf.openSession()) {
				
				provaDao.setSession(session);
				userDao.setSession(session);
				
				// phase 1 : add data to database
				
				session.beginTransaction();

				provaDao.create("Provola","ciao questa � desc");
				provaDao.create("Nervino","descrizione unica");
				provaDao.create("Ciccio","balla ccciccio");

				List<Prova> all= provaDao.findAll();
								
								
				session.getTransaction().commit();
				
				session.beginTransaction();

				System.out.println("Numero di prove: " + all.size());
				for (Prova p : all) 
				{
					System.out.println(" - " + p.getProvaId() + "_" + p.getTitle() + " : " + p.getDescription());
				}
				
				
				
				session.getTransaction().commit();
				
				// phase 3 : create user
				session.beginTransaction();
				
				User u1 = userDao.create(userDao.encryptPassword("user1"), "user1","cristiano","rossi");				
				User u2 = userDao.create("user1", "user2@gmail.com","matteo","bianchi");
				User u3 = userDao.create("user1", "user3@gmail.com","lorenzo","verdi");
				
				u1.setRole(false);
				u2.setRole(true);
				u3.setRole(false);
				userDao.update(u1);
				userDao.update(u2);
				userDao.update(u3);
				
				session.getTransaction().commit();
			}

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
}

