package it.univpm.hhc.model.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.hhc.model.entities.User;

@Repository("userDetailsDao")
public class UserDetailsDaoDefault extends DefaultDao implements UserDetailsDao {
	
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

	@Override
	public User update(User user) {
		return (User)this.getSession().merge(user);
	}

	@Override
	public void delete(User user) {
		this.getSession().delete(user);
	}

	@Override
	public User create(String password,String email,String name,String surname) {
		User u=new User();
		//da vedere bene, devo assegnare un carrello appena creo l'utente||||||||||||||||||||||||||||||||||||||||||||
//		GESTIRE LE ECCEZIONI
//		if ((firstName == null || firstName.length() == 0) && 
//				(lastName == null || lastName.length() == 0)) {
//			throw new RuntimeException("A singer must have a first name or a last name");
//		}
		u.setPassword(password);
		u.setEmail(email);
		u.setName(name);
		u.setSurname(surname);
		u.setRole(false); //alla creazione è false, poi gli passo il permesso per essere admin
		u.setActive(true);
		this.getSession().save(u);
		return u;
		}
	
}