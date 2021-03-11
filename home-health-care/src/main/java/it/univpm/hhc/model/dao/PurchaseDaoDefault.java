package it.univpm.hhc.model.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

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
	public List<Purchase> findByUser(User user){
		return getSession().createQuery("from Purchase p where p.user = :user", Purchase.class).setParameter("user", user).getResultList();
	}
	
	@Override
	public List<Purchase> findByAddress(Address address){
		return getSession().createQuery("from Purchase p where p.address = :address", Purchase.class).setParameter("address", address).getResultList();
				
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
