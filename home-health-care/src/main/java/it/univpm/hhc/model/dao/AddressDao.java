package it.univpm.hhc.model.dao;

import org.hibernate.Session;
import java.util.List;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.User;


public interface AddressDao {

	Session getSession();
		
	public void setSession(Session session);
		
	Address findById(Long id);
		
	List<Address> findAll();
		
	Address create(int cap, String city, String street, int civ_num, User user);
		
	Address update(Address address);
		
	void delete(Address address);	

}
