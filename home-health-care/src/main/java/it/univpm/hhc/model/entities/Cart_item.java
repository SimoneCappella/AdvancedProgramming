package it.univpm.hhc.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="cart_item")
public class Cart_item {
	private Long cart_item_id;
	private Long cart_code;
	private Long item_code;
	private int quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ITEM_ID")
	public Long getCart_item_id() {
		return cart_item_id;
	}
	
	public void setCart_item_id(Long cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
	
	@Column(name = "CART_CODE")
	public Long getCart_code() {
		return cart_code;
	}
	
	public void setCart_code(Long cart_code) {
		this.cart_code = cart_code;
	}
	
	@Column(name = "USER_CODE")
	public Long getItem_code() {
		return item_code;
	}
	
	public void setItem_code(Long item_code) {
		this.item_code = item_code;
	}
	
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
