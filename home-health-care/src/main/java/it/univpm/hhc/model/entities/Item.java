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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "item")
public class Item implements Serializable {
	
	private Long id_item;
	private String title;
	private String description;
	private double price;
	private String image;
	private Set<Cart_item> cart_items = new HashSet<Cart_item>();
  
  @Id	
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ITEM")
  public Long getItemId() {
	  return this.id_item;
  }
  public void setItemId(Long id_item) {
	  this.id_item = id_item;
  }
  
  @Column(name = "TITLE")
  public String getItemTitle() {
	  return this.title;
  }
  
  public void setItemTitle(String title) {
	  this.title = title;
  }
  
  @Column(name = "DESCRIPTION")
  public String getItemDescription() {
	  return this.description;
  }
  public void setItemDescription(String description) {
	  this.description = description;
  }

  @Column(name = "PRICE")
  public double getItemPrice() {
	  return this.price;
  }
  
  public void setItemPrice(double price) {
	  this.price = price;
  }

  @Column(name = "IMAGE")
  public String getItemImage() {
	  return this.image;
  }
  
  public void setItemImage(String image) {
	  this.image = image;
  }

  @OneToMany(mappedBy="item", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Cart_item> getCart_items(){
		return this.cart_items;
	}
	
	public void addCart_item(Cart_item cart_item) {
		cart_item.setItem(this);
		this.cart_items.add(cart_item);
	}
	
	public void setCart_items(Set<Cart_item> cart_items) {
		this.cart_items = cart_items;
	}
  
  /*
   //Relazione MM tra item e cart
   @ManyToMany(mappedBy = "items")
	private Set<Cart> carts = new HashSet<Cart>();
  
 
 /*   
  public boolean isEnabled() {
	  return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
	  this.enabled = enabled;
  } */
  
}