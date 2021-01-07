package it.univpm.hhc.model.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.hhc.model.entities.Prova;

//@Transactional
@Repository("provaDao")
public class ProvaDaoDefault extends DefaultDao implements ProvaDao {
	
	@Override
//	@Transactional(readOnly = true)
	public List<Prova> findAll() {
		return getSession().
			createQuery("from Prova p", Prova.class).
			getResultList();
	}

	@Override
//	@Transactional(readOnly = true)
	public Prova findById(Long id) {
		return getSession().find(Prova.class, id);
	}

	@Override
//	@Transactional
	public void delete(Prova prova) {
		this.getSession().delete(prova);
	}

	@Override
//	@Transactional
	public Prova create(String title) {
		Prova p=new Prova();
		p.setTitle(title);
		this.getSession().save(p);
		return p;
	}

	@Override
//	@Transactional
	public Prova update(Prova prova) {
		return (Prova)this.getSession().merge(prova);
	}

}
