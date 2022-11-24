package com.app.service;

import java.util.List;

import com.app.exception.AddressException;
import com.app.model.Address;



public interface AddressService {

	
	public Address addAddress(Address address) throws AddressException;
		
	public Address updateAddress(Address address) throws AddressException;
	
	
	public Address removeAddress(Integer address)throws AddressException;
	public List<Address> viewAllAddress() throws AddressException;
	
	public Address viewAddress(Integer customerId) throws AddressException;
		
	
	
}
