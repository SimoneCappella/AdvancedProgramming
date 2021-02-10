package it.univpm.hhc.model.dao;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.User;

@Repository("userDetailsDao")
public class UserDetailsDaoDefault extends DefaultDao implements UserDetailsDao {
	

	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll() {
		return getSession().
			createQuery("from User s", User.class).
			getResultList();
	}

	@Override
	public User findById(Long id) {
		return getSession().find(User.class, id);
	}

	/*@Override
	public void update(User user) {
		Query q = this.getSession().createQuery("from USER where 'USER_ID' ='" + user.getUser_id() + "'");
		User result = (User)q.getResultList();
		this.getSession().update(result);
	}*/
	
	@Override
	public void update(User user) {
		//user.setPassword(encryptPassword(user.getPassword()));
		this.getSession().update(user);		
	}

	@Override
	public void delete(User user) {
		this.getSession().delete(user);
	}

	@Override
	public User create(String password,String email,String name,String surname) {
		User u=new User();
		Cart cart= new Cart();
		//da vedere bene, devo assegnare un carrello appena creo l'utente||||||||||||||||||||||||||||||||||||||||||||
//		GESTIRE LE ECCEZIONI
//		if ((firstName == null || firstName.length() == 0) && 
//				(lastName == null || lastName.length() == 0)) {
//			throw new RuntimeException("A singer must have a first name or a last name");
//		}

		setPasswordEncoder(new BCryptPasswordEncoder());
		u.setPassword(encryptPassword(password));
		u.setEmail(email);
		u.setName(name);
		u.setSurname(surname);
		u.setRole(false);
		u.setActive(true);
		this.getSession().save(u);
		return u;
		}

	@Override
	public User findByEmail(String email) {
		return getSession().createQuery("from User u where email = '"+ email+"'", User.class).getSingleResult();
		
		
	}
	@Override
	public String encryptPassword(String password) {
		return this.passwordEncoder.encode(password);
	}
	
	@Override
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public PasswordEncoder getpasswordEncoder() {
		return this.passwordEncoder;
	}
	
}