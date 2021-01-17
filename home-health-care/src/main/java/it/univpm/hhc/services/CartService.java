package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.User;

public interface CartService {
	
	List<Cart> findAll();
	
	Cart findById(Long id);
	
	Cart create (int item_num, double total, User user);
	
	Cart update(Cart cart);
	
	void delete(Long cartId);

}
