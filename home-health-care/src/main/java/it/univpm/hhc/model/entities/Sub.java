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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sub")
public class Sub implements Serializable {
	
	private Long sub_id;
	private String name;
	private Integer discount; //sconto in percentuale
	private Double price;
	
	private Set<User> users = new HashSet<User>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUB_ID")
	public Long getSub_id() {
		return sub_id;
	}
	public void setSub_id(Long sub_id) {
		this.sub_id = sub_id;
	}
	

	@Column(name = "NAME", nullable = false) 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "DISCOUNT", nullable = false) 
	public Integer getDiscount() {
		return discount;
	}
	
	public void setDiscount(Integer discount) {
		this.discount= discount;
	}
	
	@Column(name = "PRICE", nullable = false) 
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price= price;
	
	}
	
	@OneToMany(mappedBy="sub", cascade=CascadeType.ALL,
			orphanRemoval=true)
	public Set<User> getUsers() {
		return this.users;
	}
	
	public void addUser(User user) {
		user.setSub(this);
		this.users.add(user);
		
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	

}
