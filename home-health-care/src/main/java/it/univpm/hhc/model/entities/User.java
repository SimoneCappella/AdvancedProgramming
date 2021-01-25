package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "user")
public class User implements Serializable {
	private Long user_id;
	private Sub sub;
	private Long address_code;
	private String password;
	private String email;
	private String name;
	private String surname;
	private Date subexp; //data di scadenza dell'abbonamento
	private Boolean role; // 0-false=user 1-true=admin
	private boolean enabled;  // � is_enable del prof
	private Cart cart = new Cart();
	//private Set<Cart> carts = new HashSet<Cart>();
	private Set<Purchase> purchase = new HashSet<Purchase>();
	private Set<Address> address = new HashSet<Address>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Column(name = "PASSWORD", nullable = false) //nullable serve per i campi non vuoti, in questo modo dico che la pass � obbligatoria
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "EMAIL", nullable = false) 
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "NAME", nullable = false) 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "SURNAME", nullable = false) 
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SUBSCRIPTION_EXPIRATION") 
	public Date getSubexp() {
		return subexp;
	}
	
	public void setSubexp(Date subexp) {
		this.subexp= subexp;
	}
	
	@Column(name = "ROLE", nullable = false) //nullable
	public Boolean getRole() {
		  return this.role;
	  }
	  
	public void setRole(Boolean role) {	  
		this.role= role;
	}
	
	@Column(name = "ENABLED", nullable = false) //nullable
	 public boolean isEnabled() {
		return this.enabled;
	}
	  
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	} 
	
	
	@ManyToOne
	@JoinColumn(name = "SUB_CODE")
	public Sub getSub() {
		return this.sub;
	}
	
	public void setSub(Sub sub) {
		this.sub = sub;
	}
	
	/*
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Cart> getCarts(){
		return this.carts;
	}*/
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Cart getCarts(){
		return this.cart;
	}
	
	/*
	public void addCart(Cart cart) {
		cart.setUser(this);
		this.carts.add(cart);
	}*/
	
	public void addCart(Cart cart) {
		//cart.setUser(this);
		this.cart = cart;
	}
	
	/*
	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}*/
	
	public void setCarts(Cart cart) {
		this.cart = cart;
	}
  
	//relazione OneToMany con purchase
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Purchase> getPurchase(){
		return this.purchase;
	}
	
	public void addPurchase(Purchase purchase) {
		purchase.setUser(this);
		this.purchase.add(purchase);
	}
	
	public void setPurchase(Set<Purchase> purchase) {
		this.purchase = purchase;
	}
	
	//relazione OneToMany con address
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Address> getAddress(){
		return this.address;
	}
	
	public void addAddress(Address address) {
		address.setUser(this);
		this.address.add(address);
	}
	
	public void setAddress(Set<Address> address) {
		this.address = address;
	}

} 