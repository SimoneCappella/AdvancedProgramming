
package it.univpm.hhc.services;

import java.util.List;

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.User;

public interface AddressService {
	
	List<Address> findAll();
	
	Address findById(Long id);
	
	Address create (String cap, String city, String street, String civ_num, User user);
	
	List<Address> findByUserId(User user);
	
	Address update(Address address);
	
	void delete(Long addressId);

}
