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
	public Prova findById(String id) {
		return getSession().find(Prova.class, id);
	}

	@Override
	public Prova update(Prova prova) {
		return (Prova)this.getSession().merge(prova);
	}

	@Override
//	@Transactional
	public void delete(Prova prova) {
		this.getSession().delete(prova);
	}

	@Override
//	@Transactional
	public Prova create(String id,String title, String description) {
		Prova p=new Prova();
		p.setProvaId(id);
		p.setTitle(title);
		p.setDescription(description);
		this.getSession().save(p);
		return p;
		}

	@Override
//	@Transactional
	public Prova findByTitle(String title) {
		return this.getSession().createQuery("FROM Prova p WHERE p.title = :title", Prova.class).setParameter("title", title).getSingleResult();
	}

}
