package it.univpm.hhc.model.dao;

import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.User;


public interface PurchaseDao {
	
	Session getSession();
	
	public void setSession(Session session);
	
	Purchase findById(Long id);
	
	List<Purchase> findAll();
	
	List<Purchase> findByUser(User user);
	
	Purchase create(String pay_method, LocalDate date, double total, User user, Address address);
	
	Purchase update(Purchase purchase);
	
	void delete(Purchase purchase);

}
