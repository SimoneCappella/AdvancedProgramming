package it.univpm.hhc.model.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.User;

@Repository("addressDao")
public class AddressDaoDefault extends DefaultDao implements AddressDao {
	
	@Override
	public Address findById(Long id) {
		return getSession().find(Address.class, id);
	}

	@Override
	public List<Address> findAll() { 
		return getSession().createQuery("from Address a", Address.class).getResultList();
	}
	
	@Override
	public List<Address> findByUserId(Long user_code){
		List<Address> ciao =  getSession().createQuery("from Address a where USER_CODE='" + user_code +"'", Address.class).getResultList();
		return ciao;
	}

	@Override
	public Address create(String cap, String city, String street, String civ_num, User user) {
		Address a = new Address();
		a.setCap(cap);
		a.setCity(city);
		a.setStreet(street);
		a.setCiv_num(civ_num);
		a.setUser(user); 
		this.getSession().save(a);
		return a;
	}

	@Override
	public Address update(Address address) {
		return (Address) this.getSession().merge(address);
	}

	@Override
	public void delete(Address address) {
		this.getSession().delete(address);
	}
	
}
