package it.univpm.hhc.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart implements Serializable {
	private Long cart_id;
	private Long user_code;
	private double total;
	private int item_num;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	public Long getCart_id() {
		return cart_id;
	}
	
	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}
	
	@Column(name = "USER_CODE")
	public Long getUser_code() {
		return user_code;
	}
	
	public void setUser_code(Long user_code) {
		this.user_code = user_code;
	}
	
	@Column(name = "TOTAL")
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	@Column(name = "ITEM_NUM")
	public int getItem_num() {
		return item_num;
	}
	
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	
}
