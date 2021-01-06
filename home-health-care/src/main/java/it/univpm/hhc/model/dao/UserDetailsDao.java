package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.univpm.hhc.model.entities.User;


public interface UserDetailsDao {
	Session getSession();
	public void setSession(Session session);

	
	User findUserByUsername(String username);
	
	User create(String username, String password, boolean isEnabled);
	
	User update(User user);
	
	void delete(User user);

	public String encryptPassword(String password);
	
	void setPasswordEncoder(PasswordEncoder passwordEncoder);
	
	PasswordEncoder getpasswordEncoder();
}
