package it.univpm.hhc.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.hhc.model.entities.Prova;

public interface ProvaDao {
	Session getSession();
	public void setSession(Session session);

	Prova findByTitle(String name);
	
	List <Prova> findAll();
	
	Prova findById(String id);

	Prova create(String id, String title, String description);
	
	Prova update(Prova prova);
	
	void delete(Prova contact);


}
