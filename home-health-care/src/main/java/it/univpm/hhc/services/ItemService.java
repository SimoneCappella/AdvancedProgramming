package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Item;

public interface ItemService {
	
	List<Item> findAll();
	
	Item findById(Long id);
	
	Item create (Long id_item,
	String title,
	String description,
	double price,
	String image);
	
	Item update(Item item);
	
	void delete(Long id_item);

}
