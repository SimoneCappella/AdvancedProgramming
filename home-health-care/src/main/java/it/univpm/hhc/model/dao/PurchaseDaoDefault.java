package it.univpm.hhc.model.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.User;



@Repository("purchaseDao")
public class PurchaseDaoDefault extends DefaultDao implements PurchaseDao {
	
	@Override
	public Purchase findById(Long id) {
		return getSession().find(Purchase.class, id);
	}

	@Override
	public List<Purchase> findAll() { 
		return getSession().createQuery("from Purchase p", Purchase.class).getResultList();
	}
	
	@Override
	public List<Purchase> findByUserId(Long id){
		return getSession().createQuery("from Purchase p where USER_CODE ='" + id + "'", Purchase.class).getResultList();
	}

	@Override
	public Purchase create(String pay_method, LocalDate date, double total, User user, Address address) {
		Purchase p = new Purchase();
		p.setPay_method(pay_method);
		p.setDate(date);
		p.setTotal(total);
		p.setUser(user);
		p.setAddress(address);
		this.getSession().save(p);
		return p;
	}

	@Override
	public Purchase update(Purchase purchase) {
		return (Purchase) this.getSession().merge(purchase);
	}

	@Override
	public void delete(Purchase purchase) {
		this.getSession().delete(purchase);
	}
	
}
