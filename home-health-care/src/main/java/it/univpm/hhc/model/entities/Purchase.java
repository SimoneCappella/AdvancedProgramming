
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {
	
	private Long purchase_id;
	private Cart cart;
	private String pay_method;
	private String date;
	private User user;
	private double total;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_ID")
	public Long getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(Long purchase_id) {
		this.purchase_id = purchase_id;
	}
	
	@ManyToOne
	@JoinColumn(name = "CART_CODE")
	public Cart getCart() {
		return this.cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	@Column(name = "PAY_METHOD", nullable = false) 
	public String getPay_method() {
		return pay_method;
	}
	
	public void setPay_method(String pay_method) {
		this.pay_method= pay_method;
	}
	
	@Column(name = "DATE", nullable = false) 
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date= date;
	
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_CODE", referencedColumnName = "USER_ID")
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Column(name = "TOTAL", nullable = false) 
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total= total;
	
	}
}