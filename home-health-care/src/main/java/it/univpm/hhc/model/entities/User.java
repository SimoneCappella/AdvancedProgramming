package it.univpm.hhc.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
	
  @Id
  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "ENABLED", nullable = false)
  private boolean enabled;

  @ManyToMany
  @JoinTable( 
      name = "users_roles", 
      joinColumns = @JoinColumn(
        name = "username", referencedColumnName = "username"), 
      inverseJoinColumns = @JoinColumn(
        name = "role_id", referencedColumnName = "id")) 
  private Set<Role> roles = new HashSet<Role>();
  
  @OneToMany(mappedBy="user")
  private Set<Authorities> authorities = new HashSet<Authorities>();
  
  public String getUsername() {
	  return this.username;
  }
  
  public void setUsername(String username) {
	  this.username = username;
  }
  
  public String getPassword() {
	  return this.password;
  }
  
  public void setPassword(String password) {
	  this.password = password;
  }
  
  public boolean isEnabled() {
	  return this.enabled;
  }
  
  public void setEnabled(boolean enabled) {
	  this.enabled = enabled;
  } 
  
  /*
  public Set<Role> roles () {
	  return this.roles;
  }*/

  public Set<Role> getRoles() {
	  return this.roles;
  }
  
  public void addRole(Role role) {
	  if (this.roles == null) {
		  this.roles = new HashSet<Role>();
	  }
	  
	  this.roles.add(role);
  }
  
  public void setRoles(Set<Role> roles) {
	  this.roles = roles;
  }
  
  
  
}