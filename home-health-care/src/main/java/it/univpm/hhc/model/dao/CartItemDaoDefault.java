package it.univpm.hhc.model.dao;

import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Repository;


import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.model.entities.User;

@Repository("cartItemDao")
public class CartItemDaoDefault extends DefaultDao implements CartItemDao {
	
	@Override
	public Cart_item findById(Long id) {
		return  getSession().find(Cart_item.class, id);
	}
	
	@Override
	public List<Cart_item> findByCart(Long id) {
		return getSession().createQuery("from Cart_item c where cart_code = '"+id+"'", Cart_item.class).getResultList();
	}

	@Override
	public List<Cart_item> findAll() { 
		return getSession().createQuery("from Cart_item c", Cart_item.class).getResultList();
	}

	@Override
	public Cart_item create(Cart cart, Long item_code, int quantity) {
		Cart_item c = new Cart_item();
		c.setCart(cart);
		c.setItem_code(item_code);
		c.setQuantity(quantity);
		return c;
	}

	@Override
	public Cart_item update(Cart_item cart_item) {
		return (Cart_item) this.getSession().merge(cart_item);
	}

	@Override
	public void delete(Cart_item cart_item) {
		this.getSession().delete(cart_item);
	}
	
}