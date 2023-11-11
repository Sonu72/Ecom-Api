package com.sk.service;

import java.util.List;

import com.sk.exception.CustomerException;
import com.sk.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer remove(Integer customerId) throws CustomerException;

	public List<Customer> viewAllCustomer() throws CustomerException;

}
