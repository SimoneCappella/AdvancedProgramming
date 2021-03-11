package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;

public interface UserService {

	List<User> findAll();
	
	User findById(Long id);
	
	List<User> findByEmail2(String email);
	
	User findByEmail(String email);
	
	User create( String password,String email,String name,String surname);
	
	void update(User user);
	
	List <User> findBySub(Sub sub);
	
	void updatewithpass(User user);
	
	void delete(Long userId);
	
	
}
