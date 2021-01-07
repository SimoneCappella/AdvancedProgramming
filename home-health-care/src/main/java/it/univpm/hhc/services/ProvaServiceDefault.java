package it.univpm.hhc.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.hhc.model.dao.ProvaDao;
import it.univpm.hhc.model.entities.Prova;
//con transactional abbiamo la gestione automatica delle transazioni, con service indichiamo che stiamo facendo un servizio
@Transactional
@Service("provaService")
public class ProvaServiceDefault implements ProvaService {

	ProvaDao provaDao;
	
	@Override
	public List<Prova> findAll() {
		return this.provaDao.findAll();
	}

	@Override
	public Prova findById(String id) {
		return this.provaDao.findById(id);
	}

	@Override
	public Prova create(String id, String title, String description) {
		return this.provaDao.create(id, title, description);
	}
	
	@Override
	public Prova update(Prova prova) {
		return this.provaDao.update(prova);
	}

	@Override
	public void delete(String provaId) {
		Prova prova= this.provaDao.findById(provaId);
		this.provaDao.delete(prova);

	}
	
	@Autowired
	public void setProvaDao(ProvaDao provaDao) {
		this.provaDao = provaDao;
	}
}
