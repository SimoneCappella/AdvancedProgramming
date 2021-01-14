
package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Boolean role; // 0=user 1=admin
	private Boolean active; // è is_enable del prof

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Column(name = "PASSWORD", nullable = false) //nullable serve per i campi non vuoti, in questo modo dico che la pass è obbligatoria
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
	
	@Column(name = "ROLE", nullable = false)
	public boolean getRole() {
		  return this.role;
	  }
	  
	public void setRole(boolean role) {	  
		this.role= role;
	}
	
	@Column(name = "ACTIVE", nullable = false)
	public boolean isActive() {
		  return this.active;
	  }
	  
	public void setActive(boolean active) {	  
		this.active = active;
	}
	
	@ManyToOne
	@JoinColumn(name = "SUB_CODE")
	public Sub getSub() {
		return this.sub;
	}
	
	public void setSub(Sub sub) {
		this.sub = sub;
	}
  
} 