package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.User;

public interface PurchaseService {
	
	List<Purchase> findAll();
	
	Purchase findById(Long id);
	
	Purchase create (String pay_method, String date, double total, User user);
	
	Purchase update(Purchase purchase);
	
	void delete(Long purchaseId);

}
