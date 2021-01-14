package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="address")

public class Address implements Serializable {

	private Long id_address;
	private int cap;
	private String city;
 	private String street;
	private int civ_num;
	
   

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADDRESS")
	public Long getId_address() {
		return id_address;
	}
              public void setId_address(Long id_address){
		this.id_address = id_address;
	}
	
	@Column(name = "CAP")
	public int getCap() {
		return cap;
	}

              public void setCap(int cap){
		this.cap = cap;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
@Column(name = "STREET")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street){
		this.street = street;
	}
@Column(name = "CIV_NUM")
	public int getCiv_num() {
		return civ_num;
	}
	public void setCiv_num(int civ_num){
		this.civ_num = civ_num;
	}

}
