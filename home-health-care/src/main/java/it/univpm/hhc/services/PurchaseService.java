package it.univpm.hhc.services;

import java.time.LocalDate;
import java.util.List;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.User;

public interface PurchaseService {
	
	List<Purchase> findAll();
	
	List<Purchase> findByUser(User user);
	
	Purchase findById(Long id);
	
	Purchase create (String pay_method, LocalDate date, double notdiscountedtotal, double total, User user, Address address);
	
	Purchase update(Purchase purchase);
	
	void delete(Long purchaseId);
	
	List<Purchase> findByAddress(Address address);
	

}
