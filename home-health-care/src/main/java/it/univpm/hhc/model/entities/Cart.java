package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart implements Serializable {
	private Long cart_id;
	private User user;
	private double total;
	private int item_num;
	
	private Set<Cart_item> cart_items = new HashSet<Cart_item>();
	//private Set<Item> items = new HashSet<Item>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	public Long getCart_id() {
		return cart_id;
	}
	
	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_CODE", referencedColumnName = "USER_ID")
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Cart_item> getCart_items(){
		return this.cart_items;
	}
	
	public void addCart_item(Cart_item cart_item) {
		cart_item.setCart(this);
		this.cart_items.add(cart_item);
	}
	
	public void setCart_items(Set<Cart_item> cart_items) {
		this.cart_items = cart_items;
	}
	
}
