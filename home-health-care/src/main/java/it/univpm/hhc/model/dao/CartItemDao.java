package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import java.util.List;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Cart_item;

public interface CartItemDao {

	Session getSession();
	
	public void setSession(Session session);
	
	Cart_item findById(Long id);
	
	List<Cart_item> findByCart (Long id);
	
	List<Cart_item> findAll();
	
	Cart_item create(Cart cart, Long item_code, int quantity);
	
	Cart_item update(Cart_item cart_item);
	
	void delete(Cart_item cart_item);
	
}

