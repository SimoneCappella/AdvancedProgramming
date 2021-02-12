
package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.univpm.hhc.utils.LocalDateAttributeConverter;

@Entity
@Table(name = "purchase")
public class Purchase implements Serializable {
	
	private Long purchase_id;
	private String pay_method;
	private LocalDate date;
	private User user;
	private Address address;
	private double total;
	private Set<Cart_item> cart_items = new HashSet<Cart_item>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PURCHASE_ID")
	public Long getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(Long purchase_id) {
		this.purchase_id = purchase_id;
	}
	
	
	@Column(name = "PAY_METHOD", nullable = false) 
	public String getPay_method() {
		return pay_method;
	}
	
	public void setPay_method(String pay_method) {
		this.pay_method= pay_method;
	}
	
	@Convert(converter = LocalDateAttributeConverter.class)
	@Column(name = "DATE") 
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date= date;
	
	}
	
	//relazione ManyToOne con user
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_CODE")
	public User getUser() {
		return this.user;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_CODE", referencedColumnName = "address_id")
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	//Relazione OneToMany con Cart_item
	@OneToMany(mappedBy="purchase", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Cart_item> getCart_items(){
		return this.cart_items;
	}
	
	public void addCart_item(Cart_item cart_item) {
		cart_item.setPurchase(this);
		this.cart_items.add(cart_item);
	}
	
	public void setCart_items(Set<Cart_item> cart_items) {
		this.cart_items = cart_items;
	}

	
	@Column(name = "TOTAL") 
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total= total;
	
	}
}