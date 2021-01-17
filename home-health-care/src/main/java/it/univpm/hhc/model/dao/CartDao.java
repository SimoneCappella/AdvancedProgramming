package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import java.util.List;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.User;

public interface CartDao {

	Session getSession();
	
	public void setSession(Session session);
	
	Cart findById(Long id);
	
	List<Cart> findAll();
	
	Cart create(int item_num, double total, User user);
	
	Cart update(Cart cart);
	
	void delete(Cart cart);
	
}
