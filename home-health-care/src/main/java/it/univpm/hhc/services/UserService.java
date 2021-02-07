package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.User;

public interface UserService {

	List<User> findAll();
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	User create( String password,String email,String name,String surname);
	
	void update(User user);
	
	void delete(Long userId);
	
	
}
