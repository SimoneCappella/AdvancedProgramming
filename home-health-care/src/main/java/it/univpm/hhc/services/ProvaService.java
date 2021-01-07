package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Prova;

public interface ProvaService {

	List<Prova> findAll();
	
	Prova findById(String id);
	
	Prova create(String id, String title, String description);
	
	Prova update(Prova prova);
	
	void delete(String instrumentId);
	
	
}
