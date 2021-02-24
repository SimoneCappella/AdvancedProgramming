package it.univpm.hhc.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="cart_item")
public class Cart_item implements Serializable{
	private Long cart_item_id;
	private Cart cart;
	private Item item;
	private int quantity;
	private Purchase purchase;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ITEM_ID")
	public Long getCart_item_id() {
		return cart_item_id;
	}
	
	public void setCart_item_id(Long cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
	
	@ManyToOne
	@JoinColumn(name = "CART")
	public Cart getCart() {
		return this.cart;
	}
	
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	@ManyToOne
	@JoinColumn(name = "ITEM")
	public Item getItem() {
		return this.item;
	}
	
	
	public void setItem(Item item) {
		this.item = item;
	}
	

	//Relazione ManyToOne con Purchase
	@ManyToOne
	@JoinColumn(name = "PURCHASE")
	public Purchase getPurchase() {
		return this.purchase;
	}
	
	
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
