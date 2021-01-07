package it.univpm.hhc.test;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
			
			try (Session session = sf.openSession()) {
				
				provaDao.setSession(session);
				userDao.setSession(session);
				
				// phase 1 : add data to database
				
				session.beginTransaction();

				provaDao.create("Provola");
				provaDao.create("Nervino");
				provaDao.create("Ciccio");

				List<Prova> all= provaDao.findAll();
								
								
				session.getTransaction().commit();
				
				session.beginTransaction();

				System.out.println("Numero di prove: " + all.size());
				for (Prova p : all) 
				{
					System.out.println(" - " + p.getId() + "_" + p.getId() + " : " + p.getVersion());
				}
				
				
				
				session.getTransaction().commit();
				
				// phase 3 : create user
				session.beginTransaction();
				
				User u1 = userDao.create("user1", "user1", true);				
				User u2 = userDao.create("user2", "user2", true);				
				User u3 = userDao.create("user3", "user3", true);				
				
				userDao.update(u1);
				userDao.update(u2);
				session.getTransaction().commit();
			}

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
}

