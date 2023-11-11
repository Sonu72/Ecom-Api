
package com.sk.service;

import java.util.List;

import com.sk.exception.AddressException;
import com.sk.exception.CustomerException;
import com.sk.exception.SessionLoginException;
import com.sk.model.Address;

public interface AddressService {

	public Address updateAddressByUserId(Address address, Integer userId, String key)
			throws AddressException, CustomerException, SessionLoginException;

	public List<Address> viewAllAddress(String key) throws AddressException, SessionLoginException;

	public Address viewAddressByUserId(Integer userId, String key) throws CustomerException, SessionLoginException;

}
