package it.univpm.hhc.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.hhc.model.entities.Sub;

public interface SubDao {
	Session getSession();
	public void setSession(Session session);

	Sub findByTitle(String name);
	
	List <Sub> findAll();
	
	Sub findById(Long id);

	Sub create(String name, Double price, int discount);
	
	Sub update(Sub sub);
	
	void delete(Sub contact);


}
