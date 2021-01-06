package it.univpm.hhc.services;

import it.univpm.hhc.model.entities.User;

public interface UserService {
	public User findById(String username);
	public User create(String username, String password);
	public void delete(String username);
	public User update(User user);
}
