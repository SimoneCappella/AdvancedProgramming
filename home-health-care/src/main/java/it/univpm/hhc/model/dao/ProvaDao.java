package it.univpm.hhc.model.dao;

import java.util.List;

import org.hibernate.Session;

import it.univpm.hhc.model.entities.Prova;

public interface ProvaDao {
	Session getSession();
	public void setSession(Session session);

	
	List<Prova> findAll();
	
	Prova findById(Long id);

	Prova create(String title);
	
	Prova update(Prova prova);
	
	void delete(Prova prova);


}
