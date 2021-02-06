package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Sub;

public interface SubService {

	List<Sub> findAll();
	
	Sub findById(Long id);
	
	Sub create( String name, Double price, int discount);
	
	Sub update(Sub sub);
	
	void delete(Long subId);
	
	
}
