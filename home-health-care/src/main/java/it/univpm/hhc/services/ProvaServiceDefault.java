package it.univpm.hhc.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.hhc.model.dao.ProvaDao;
import it.univpm.hhc.model.entities.Prova;

@Transactional
@Service("albumService")
public class ProvaServiceDefault implements ProvaService {

	private ProvaDao provaDao;
	
	@Override
	public List<Prova> findAll() {
		return this.provaDao.findAll();
	}

	@Override
	public Prova create(String title) {
		return this.provaDao.create(title);
	}
	
	@Override
	public Prova findById(Long id) {
		return this.provaDao.findById(id);
	}

	@Override
	public void delete(Prova prova) {
		this.provaDao.delete(prova);

	}

	@Override
	public Prova update(Prova prova) {
		return this.provaDao.update(prova);
	}

	@Autowired
	public void setProvaDao(ProvaDao provaDao) {
		this.provaDao = provaDao;
	}
}
