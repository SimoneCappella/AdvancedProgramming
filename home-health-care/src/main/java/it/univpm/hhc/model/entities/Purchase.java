
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
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {
	
	private Long purchase_id;
	private Long cart_code;
	private String pay_method;
	private String date;
	private Long user_code;
	private Long add_code;
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
	

	@Column(name = "CART_CODE", nullable = false) 
	public Long getCart_code() {
		return cart_code;
	}
	
	public void setCart_code(Long cart_code) {
		this.cart_code = cart_code;
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
	
	
	@Column(name = "USER_CODE", nullable = false) 
	public Long getUser_code() {
		return user_code;
	}
	
	public void setUser_code(Long user_code) {
		this.user_code= user_code;
	
	}
	
	@Column(name = "ADD_CODE", nullable = false) 
	public Long getAdd_code() {
		return add_code;
	}
	
	public void setAdd_code(Long add_code) {
		this.add_code= add_code;
	
	}
	@Column(name = "TOTAL", nullable = false) 
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total= total;
	
	}
}