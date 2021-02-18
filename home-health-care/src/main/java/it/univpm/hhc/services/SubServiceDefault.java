package it.univpm.hhc.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univpm.hhc.model.dao.SubDao;
import it.univpm.hhc.model.entities.Sub;
//con transactional abbiamo la gestione automatica delle transazioni, con service indichiamo che stiamo facendo un servizio
@Transactional
@Service("subService")
public class SubServiceDefault implements SubService {

	SubDao subDao;
	
	@Override
	public List<Sub> findAll() {
		return this.subDao.findAll();
	}

	@Override
	public Sub findById(Long id) {
		return this.subDao.findById(id);
	}
	
	@Override
	public List<Sub> findByName(String name){
		return this.subDao.findByName(name);
	}

	@Override
	public Sub create(String name, Double price, int discount) {
		return this.subDao.create( name, price,discount);
	}
	
	@Override
	public Sub update(Sub sub) {
		return this.subDao.update(sub);
	}

	@Override
	public void delete(Long subId) {
		Sub sub= this.subDao.findById(subId);
		this.subDao.delete(sub);

	}
	
	@Autowired
	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}
}
