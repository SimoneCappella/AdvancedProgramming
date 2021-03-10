package it.univpm.hhc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.univpm.hhc.model.dao.ItemDao;
import it.univpm.hhc.model.entities.Item;

@Transactional
@Service("itemService")
public class ItemServiceDefault implements ItemService {
	
	ItemDao itemDao;

	@Override
	public List<Item> findAll() {
		return this.itemDao.findAll();
	}

	@Override
	public Item findById(Long id) {
		return this.itemDao.findById(id);
	}
	
	@Override
	public List<Item> findByIdList(Long id) {
		return this.itemDao.findByIdList(id);
	}

	@Override
	public List<Item> findByTitle(String title) {
		return this.itemDao.findByTitle(title);
	}
	
	@Override
	public Item create(//Long id_item,
			String title,
			String description,
			double price,
			String image) {
		return this.itemDao.create(//id_item,
				title,
				description,
				price,
				image);
	}

	@Override
	public void update(Item item) {
		this.itemDao.update(item);
	}

	@Override
	public void delete(Long itemId) {
		Item item = this.itemDao.findById(itemId);
		this.itemDao.delete(item);
	}
	
	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
