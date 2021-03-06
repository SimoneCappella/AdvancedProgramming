package it.univpm.hhc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.hhc.model.dao.CartItemDao;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.Cart_item;

@Transactional
@Service("cartItemService")
public class CartItemServiceDeafault implements CartItemService{
	
	CartItemDao cartItemDao;

	@Override
	public List<Cart_item> findAll() {
		return this.cartItemDao.findAll();
	}

	@Override
	public Cart_item findByid(Long id) {
		return this.cartItemDao.findById(id);
	}
	
	@Override
	public List<Cart_item> findByCart(Cart cart) {
		return this.cartItemDao.findByCart(cart);
	}
	@Override
	public List<Cart_item> findByPurchase(Purchase purchase){
		return this.cartItemDao.findByPurchase(purchase);
	}
	@Override
	public List<Cart_item> findByCart_item(Cart cart, Item item)
	{
		return this.cartItemDao.findByCart_item(cart, item);
	}
	@Override
	public Cart_item create(Cart cart, Item item, int quantity) {
		return this.cartItemDao.create(cart, item, quantity);
	}

	@Override
	public Cart_item update(Cart_item cart_item) {
		return this.cartItemDao.update(cart_item);
	}

	@Override
	public void delete(Long cart_item_id) {
		Cart_item cart_item = this.cartItemDao.findById(cart_item_id);
		this.cartItemDao.delete(cart_item);
	}
	
	@Autowired
	public void setCartItemDao(CartItemDao cartItemDao) {
		this.cartItemDao = cartItemDao;
	}
	

}
