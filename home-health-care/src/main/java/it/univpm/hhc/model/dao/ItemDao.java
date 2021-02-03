package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import java.util.List;
import it.univpm.hhc.model.entities.Item;

public interface ItemDao {

	Session getSession();
	
	public void setSession(Session session);
	
	Item findById(Long id_item);
	
	Item findByTitle(String title);
	
	List<Item> findAll();
	
	Item create(Long id_item,
			String title,
			String description,
			double price,
			String image);
	
	Item update(Item item);
	
	void delete(Item item);
	
}
