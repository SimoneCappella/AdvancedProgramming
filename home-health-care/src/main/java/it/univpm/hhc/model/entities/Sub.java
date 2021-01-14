package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "SUB")
public class Sub implements Serializable {
	private Long sub_id;
	private String name;
	private Double discount; //sconto in percentuale
	private Double price;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUB_ID")
	public Long getSub_id() {
		return sub_id;
	}	

	@Column(name = "NAME", nullable = false) 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "DISCOUNT", nullable = false) 
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount= discount;
	}
	
	@Column(name = "PRICE", nullable = false) 
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price= price;
	
	} 
}