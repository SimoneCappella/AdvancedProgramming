package it.univpm.hhc.model.entities;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "item")
public class Item implements Serializable {
	
  @Id	
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_ITEM")
  private String id_item;
  
  @Column(name = "TITLE", nullable = false)
  private String title;
  
  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "PRICE", nullable = false)
  private double price;

  @Column(name = "IMAGE")
  private String image;

  
  public String getItemId() {
	  return this.id_item;
  }
  
  public String getTitle() {
	  return this.title;
  }
  
  public void setTitle(String title) {
	  this.title = title;
  }
  
  public String getDescription() {
	  return this.description;
  }
  
  public void setDescription(String description) {
	  this.description = description;
  }
  
  public String getPrice() {
	  return this.description;
  }
  
  public void setPrice(double price) {
	  this.price = price;
  }
  
  public String getImage() {
	  return this.image;
  }
  
  public void setImage(String image) {
	  this.image = image;
  }
  
 /*   
  public boolean isEnabled() {
	  return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
	  this.enabled = enabled;
  } */
  
}
