package it.univpm.hhc.test;


import static org.junit.Assert.assertArrayEquals;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univpm.hhc.model.dao.CartDao;
import it.univpm.hhc.model.dao.ItemDao;
import it.univpm.hhc.model.dao.AddressDao;
import it.univpm.hhc.model.dao.SubDao;
import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Address;

public class LoadDataTest {

	public static void main(String ...args) {
		
		
//		logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {

			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			
			
			ItemDao itemDao = ctx.getBean("itemDao", ItemDao.class);
			
			AddressDao addressDao = ctx.getBean("addressDao", AddressDao.class);
			
			UserDetailsDao userDao = ctx.getBean(UserDetailsDao.class);

			CartDao cartDao=ctx.getBean("cartDao", CartDao.class);

			SubDao subDao=ctx.getBean("subDao", SubDao.class);
			
			userDao.setPasswordEncoder(new BCryptPasswordEncoder());
			
			try (Session session = sf.openSession()) {
				
				itemDao.setSession(session);
				addressDao.setSession(session);
				userDao.setSession(session);
				cartDao.setSession(session);
				subDao.setSession(session);
				// phase 1 : add data to database
				
				session.beginTransaction();

				itemDao.create("Siringa","Siringa di plastica usa e getta da 5ml",1,"media/siringa.jfif");
				itemDao.create("Tachipirina","Blister di 10 pasticche da 1000mg",5,"media/tachipirina.jpg");
				itemDao.create("Servizio Tampone Rapido","Test Rapido per rilevare la presenza di COVID-19 effettuato da un operatore sanitario",20,"media/tamp_rapid.jfif");
				itemDao.create("Servizio Tampone Molecolare","Test Molecolare per rilevare la presenza di COVID-19 effettuato da un operatore sanitario",40,"media/tamp_mole.jfif");
				itemDao.create("Mascherine pack 20","Pack da 20 mascherine usa e getta fp2 ",20,"media/mascherine.jfif");
				
				itemDao.create("Penne 500g","Confezione da 500g di penne rigate",1,"media/penne.jpg");
				itemDao.create("Spaghetti 500g","Confezione da 500g di spaghetti",1,"media/spaghetti.png");
				itemDao.create("Sugo al basilico 500g","singola confezione sugo al basilico da 500g",3,"media/sugo.jfif");
				itemDao.create("Patate 1Kg","Sacchetto di patate novelle",1.5,"media/patate.jpg");
				itemDao.create("Acqua","Confezione di 6 bottiglie da 2l di acqua naturale oligominerale San Benedetto",3.20,"media/acqua.jpg");
				
				List<Item> all= itemDao.findAll();
				session.getTransaction().commit();
				
				
				// phase 3 : create user
				session.beginTransaction();
				
				User u1 = userDao.createbytest(userDao.encryptPassword("password"), "admin1@gmail.com","Cristiano","Ronaldo");
				u1.setRole(true);
				User u2 = userDao.createbytest(userDao.encryptPassword("password"), "admin2@gmail.com","Giorgia","Marone");
				u1.setRole(true);
			
				Cart c1= cartDao.create(0, 0, u1);
				Cart c2= cartDao.create(0, 0, u2);
				Cart c3= cartDao.create(0, 0, userDao.create("password", "user1@gmail.com","Matteo","Bianchi"));
				Cart c4= cartDao.create(0, 0, userDao.create("password", "user2@gmail.com","Franco","Rossi"));
				Cart c5= cartDao.create(0, 0, userDao.create("password", "user3@gmail.com","Luca","Neri"));
				
				//Cart c3= cartDao.create(0, 0, null);
				//c3.addUser(u3);
				
				
				Sub s1=subDao.create("Standard", 15D, 10);
				subDao.update(s1);
				Sub s2=subDao.create("Premium", 30D, 30);
				subDao.update(s2);
				
				assert( c1.getTotal() == 10);
				cartDao.update(c1);
				cartDao.update(c2);
				cartDao.update(c3);
				cartDao.update(c4);
				cartDao.update(c5);
				
				addressDao.create("60122","Ancona","Via Giulio Cesare","12",c1.getUser());
				addressDao.create("60122","Ancona","Via Caio","69",c2.getUser());
				addressDao.create("60123","Ancona","Via Franco Romano","23",c2.getUser());
				addressDao.create("00123","Roma","Via Del Tricolore","68 A",c4.getUser());
				addressDao.create("00119","Roma","Via Colosseo","40 C",c3.getUser());
				
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

