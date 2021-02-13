package it.univpm.hhc.test;


import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univpm.hhc.model.dao.CartDao;
import it.univpm.hhc.model.dao.ItemDao;
import it.univpm.hhc.model.dao.ProvaDao;
import it.univpm.hhc.model.dao.AddressDao;
import it.univpm.hhc.model.dao.SubDao;
import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Prova;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Address;

public class LoadDataTest {

	public static void main(String ...args) {
		
		
//		logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			
			ProvaDao provaDao = ctx.getBean("provaDao", ProvaDao.class);
			
			ItemDao itemDao = ctx.getBean("itemDao", ItemDao.class);
			
			AddressDao addressDao = ctx.getBean("addressDao", AddressDao.class);
			
			UserDetailsDao userDao = ctx.getBean(UserDetailsDao.class);

			CartDao cartDao=ctx.getBean("cartDao", CartDao.class);

			SubDao subDao=ctx.getBean("subDao", SubDao.class);
			
			userDao.setPasswordEncoder(new BCryptPasswordEncoder());
			
			try (Session session = sf.openSession()) {
				
				itemDao.setSession(session);
				addressDao.setSession(session);
				provaDao.setSession(session);
				userDao.setSession(session);
				cartDao.setSession(session);
				subDao.setSession(session);
				// phase 1 : add data to database
				
				session.beginTransaction();

				itemDao.create("Patate","ti riempiono tanto tanto",5,"");
				itemDao.create("Libro","utile per fermare le carte",10,"");
				itemDao.create("Mattone","un bel mattone per la tua casa",15,"");
				
				List<Item> all= itemDao.findAll();
				session.getTransaction().commit();
				
				
				session.beginTransaction();
				
				provaDao.create("Provola","ciao questa desc");
				provaDao.create("Nervino","descrizione unica");
				provaDao.create("Ciccio","balla ccciccio");

				List<Prova> allProva= provaDao.findAll();
								
								
				session.getTransaction().commit();
				
				session.beginTransaction();

				System.out.println("Numero di prove: " + all.size());
				for (Prova p : allProva) 
				{
					System.out.println(" - " + p.getProvaId() + "_" + p.getTitle() + " : " + p.getDescription());
				}
				
				
				
				session.getTransaction().commit();
				
				// phase 3 : create user
				session.beginTransaction();
				
				User u1 = userDao.create("user1", "user1","cristiano","ronaldo");
				u1.setRole(true);
			
				Cart c1= cartDao.create(0, 0, u1/*userDao.create("user1", "user1","cristiano","ronaldo")*/);
				Cart c2= cartDao.create(0, 0, userDao.create("user2", "user2","matteo","bianchi"));
				Cart c3= cartDao.create(0, 0, userDao.create("user3", "user3","franco","rossi"));
				
				//Cart c3= cartDao.create(0, 0, null);
				//c3.addUser(u3);
				
				
				Sub s1=subDao.create("Poveri", 20.20, 2);
				subDao.update(s1);
				Sub s2=subDao.create("ricchi", 29.20, 20);
				subDao.update(s2);
				
				assert( c1.getTotal() == 10);
				cartDao.update(c1);
				cartDao.update(c2);
				cartDao.update(c3);
				
				addressDao.create(62017,"Citta 1","Via 1",12,c1.getUser());
				addressDao.create(62018,"Citta 2","Via 2",13,c2.getUser());
				addressDao.create(62019,"Citta 2","Via 2",14,c3.getUser());
				
				List<Address> allAddress= addressDao.findAll();
				
				session.getTransaction().commit();
			}

		} catch (Exception e) {
//			logger.error("Eccezione: " + e.getMessage());
			e.printStackTrace(System.err);
		}
//		logger.info("Esco ...");
	}
}

