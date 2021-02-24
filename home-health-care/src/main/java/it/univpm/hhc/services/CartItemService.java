package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.Cart_item;

public interface CartItemService {

	List<Cart_item> findAll();
	
	Cart_item findByid(Long id);
		
	List<Cart_item> findByCart(Cart cart);
	
	List<Cart_item> findByPurchase(Purchase purchase);
	
	Cart_item create(Cart cart, Item item, int quantity);
	
	Cart_item update(Cart_item cart_item);
	
	void delete(Long cart_item_id);
	
	List<Cart_item> findByCart_item(Cart cart, Item item);
	
}
