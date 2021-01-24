package it.univpm.hhc.services;

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
	public Item findById(Long id_item) {
		return this.itemDao.findById(id_item);
	}

	@Override
	public Item create(Long id_item,
			String title,
			String description,
			double price,
			String image) {
		return this.itemDao.create(id_item,
				title,
				description,
				price,
				image);
	}

	@Override
	public Item update(Item item) {
		return this.itemDao.update(item);
	}

	@Override
	public void delete(Long id_item) {
		Item item = this.itemDao.findById(id_item);
		this.itemDao.delete(item);
	}
	
	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
