package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import java.util.List;
import it.univpm.hhc.model.entities.Item;

public interface ItemDao {

	Session getSession();
	
	public void setSession(Session session);
	
	Item findById(Long id);
	
	List <Item> findByIdList(Long id);
	
	List<Item> findByTitle(String title);
	
	
	List<Item> findAll();
	
	Item create(//Long id_item,	//forse da togliere?
			String title,
			String description,
			double price,
			String image);
	
	void update(Item item);
	
	void delete(Item item);
	
}
