package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "item")
public class Item implements Serializable {
	
	private Long id_item;
	private String title;
	private String description;
	private double price;
	private String image;
	
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