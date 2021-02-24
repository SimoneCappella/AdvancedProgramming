package it.univpm.hhc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.univpm.hhc.model.dao.AddressDao;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.User;

@Transactional
@Service("addressService")
public class AddressServiceDefault implements AddressService {
	
	AddressDao addressDao;

	@Override
	public List<Address> findAll() {
		return this.addressDao.findAll();
	}

	@Override
	public Address findById(Long id) {
		return this.addressDao.findById(id);
	}

	@Override
	public Address create(String cap, String city, String street, String civ_num, User user) {
		return this.addressDao.create(cap, city, street, civ_num, user);
	}
	
	@Override
	public List<Address> findByUserId(User user){
		return this.addressDao.findByUserId(user);
	}

	@Override
	public Address update(Address address) {
		return this.addressDao.update(address);
	}

	@Override
	public void delete(Long addressId) {
	 Address address = this.addressDao.findById(addressId);
		this.addressDao.delete(address);
	}
	
	@Autowired
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

}
