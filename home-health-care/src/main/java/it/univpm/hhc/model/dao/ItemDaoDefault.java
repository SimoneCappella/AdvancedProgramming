package it.univpm.hhc.model.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import it.univpm.hhc.model.entities.Item;

@Repository("itemDao")
public class ItemDaoDefault extends DefaultDao implements ItemDao {

	@Override
//	@Transactional(readOnly = true)
	public List<Item> findAll() {
		return getSession().
			createQuery("from Item i", Item.class).
			getResultList();
	}

	@Override
//	@Transactional(readOnly = true)
	public Item findById(Long id_item) {
		return getSession().find(Item.class, id_item);
	}

	@Override
	public Item update(Item item) {
		return (Item)this.getSession().merge(item);
	}

	@Override
//	@Transactional
	public void delete(Item item) {
		this.getSession().delete(item);
	}

	@Override
//	@Transactional
	public Item create(Long id_item,
			String title,
			String description,
			double price,
			String image) {
		
		Item i=new Item();
		i.setItemTitle(title);
		i.setItemDescription(description);
		i.setItemPrice(price);
		i.setItemImage(image);
		this.getSession().save(i);
		return i;
		}

	@Override
//	@Transactional
	public Item findByTitle(String title) {
		return this.getSession().createQuery("FROM Item i WHERE i.title = :title", Item.class).setParameter("title", title).getSingleResult();
	}



}
