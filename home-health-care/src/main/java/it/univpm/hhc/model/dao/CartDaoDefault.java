package it.univpm.hhc.model.dao;

import java.util.List;


import org.springframework.stereotype.Repository;


import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.User;

@Repository("cartDao")
public class CartDaoDefault extends DefaultDao implements CartDao {
	
	@Override
	public Cart findById(Long id) {
		return getSession().find(Cart.class, id);
	}

	@Override
	public List<Cart> findAll() { 
		return getSession().createQuery("from Cart c", Cart.class).getResultList();
	}

	@Override
	public Cart create(int item_num, double total, User user) {
		Cart c = new Cart();
		c.setItem_num(item_num);
		c.setTotal(total);
		//c.setUser(user); //potrebbe dare errore (come passargli uno user?)
		this.getSession().save(c);
		return c;
	}
	@Override
	public Cart findByUserId(Long id) {
		return getSession().createQuery("from Cart c where USER_CODE = '" + id + "'" , Cart.class).getSingleResult();
	}


	@Override
	public Cart update(Cart cart) {
		return (Cart) this.getSession().merge(cart);
	}

	@Override
	public void delete(Cart cart) {
		this.getSession().delete(cart);
	}
	
}
