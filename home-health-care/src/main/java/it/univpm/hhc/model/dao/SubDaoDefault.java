package it.univpm.hhc.model.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import it.univpm.hhc.model.entities.Sub;

@Repository("subDao")
public class SubDaoDefault extends DefaultDao implements SubDao {
	
	@Override
	public List<Sub> findAll() {
		return getSession().
			createQuery("from Sub s", Sub.class).
			getResultList();
	}

	@Override
	public Sub findById(Long id) {
		return getSession().find(Sub.class, id);
	}

	@Override
	public Sub update(Sub sub) {
		return (Sub)this.getSession().merge(sub);
	}

	@Override
	public void delete(Sub sub) {
		this.getSession().delete(sub);
	}

	@Override
	public Sub create(String name, Double price, int discount) {
		Sub s=new Sub();
		s.setName(name);
		s.setPrice(price);
		s.setDiscount(discount);
		this.getSession().save(s);
		return s;
		}

	@Override
	public Sub findByTitle(String title) {
		return this.getSession().createQuery("FROM Sub p WHERE p.title = :title", Sub.class).setParameter("title", title).getSingleResult();
	}
	
}
