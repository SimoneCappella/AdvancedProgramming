package it.univpm.hhc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.entities.Role;
import it.univpm.hhc.model.entities.User;

public class UserDetailsServiceDefault implements UserService, UserDetailsService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDetailsDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {

			// qui "mappiamo" uno User della nostra app in uno User di spring
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());

			String[] roles = new String[user.getRoles().size()];

			int j = 0;
			for (Role r : user.getRoles()) {
				roles[j++] = r.getName();
			}

			builder.roles(roles);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}

	@Override
	public User findById(String username) {
		return this.userDetailsDao.findUserByUsername(username);
	}

	@Override
	public User create(String username, String password) {
		User newUser = this.userDetailsDao.create(username, password, false);
		return newUser;
	}

	@Override
	public void delete(String username) {
		User user = this.userDetailsDao.findUserByUsername(username);
		this.userDetailsDao.delete(user);
	}

	@Override
	public User update(User user) {
		return this.userDetailsDao.update(user);
	}
}
