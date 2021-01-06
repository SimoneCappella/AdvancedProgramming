package it.univpm.hhc.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities implements Serializable {
	
  @Id
  @Column(name = "AUTHORITY")
  private String authority;

  @ManyToOne
  @JoinColumn(name = "USERNAME")
  private User user;

  public String getAuthority() {
	  return this.authority;
  }
  
  public void setAuthority(String authority) {
	  this.authority = authority;
  }
  
  public User getUser() {
	  return this.user;
  }
  
  public void setUser(User user) { 
	  this.user = user;
  }
}