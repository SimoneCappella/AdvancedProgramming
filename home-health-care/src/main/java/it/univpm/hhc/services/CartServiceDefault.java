package it.univpm.hhc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.univpm.hhc.model.dao.CartDao;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.User;

@Transactional
@Service("cartService")
public class CartServiceDefault implements CartService {
	
	CartDao cartDao;

	@Override
	public List<Cart> findAll() {
		return this.cartDao.findAll();
	}

	@Override
	public Cart findById(Long id) {
		return this.cartDao.findById(id);
	}
	
	@Override
	public Cart findByUserId(User user) {
		return this.cartDao.findByUserId(user);
	}


	@Override
	public Cart create(int item_num, double total, User user) {
		return this.cartDao.create(item_num, total, user);
	}

	@Override
	public Cart update(Cart cart) {
		return this.cartDao.update(cart);
	}

	@Override
	public void delete(Long cartId) {
		Cart cart = this.cartDao.findById(cartId);
		this.cartDao.delete(cart);
	}
	
	@Autowired
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

}
