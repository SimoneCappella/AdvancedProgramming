package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import java.util.List;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.User;


public interface PurchaseDao {
	
	Session getSession();
	
	public void setSession(Session session);
	
	Purchase findById(Long id);
	
	List<Purchase> findAll();
	
	Purchase create(String pay_method, String date, double total, User user);
	
	Purchase update(Purchase purchase);
	
	void delete(Purchase purchase);

}
