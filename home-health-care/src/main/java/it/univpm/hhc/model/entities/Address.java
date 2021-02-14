package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {
	
	private Long address_id;
	private String cap;
	private String city;
 	private String street;//via
	private String civ_num;
	private User user;
	private Set<Purchase> purchase = new HashSet<Purchase>();

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	public Long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}
	
	//relazione ManyToOne con user
	@ManyToOne
	@JoinColumn(name = "USER_CODE")
		public User getUser() {
			return this.user;
		}
			
		public void setUser(User user) {
			this.user = user;
		}


	@Column(name = "CAP", nullable = false) 
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	@Column(name = "CITY", nullable = false) 
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city= city;
	}
	
	@Column(name = "STREET", nullable = false) 
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street= street;
	
	}
	
	
	@Column(name = "CIV_NUM", nullable = false) 
	public String getCiv_num() {
		return civ_num;
	}
	
	public void setCiv_num(String civ_num) {
		this.civ_num= civ_num;
	
	}
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Purchase> getPurchases() {
		return this.purchase;
	}
	
	public void setPurchases(Set<Purchase> purchase) {
		this.purchase = purchase;
	}

}