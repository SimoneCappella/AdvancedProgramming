package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Prova;

public interface ProvaService {

	public List<Prova> findAll();
	
	public Prova create(String name);
	
	public Prova findById(Long id);
	
	public void delete(Prova prova);
	
	public Prova update(Prova prova);

	
	
}
