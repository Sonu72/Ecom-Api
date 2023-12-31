package com.sk.servicelogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.exception.CustomerException;
import com.sk.model.Cart;
import com.sk.model.Customer;
import com.sk.repository.CartRepo;
import com.sk.repository.CustomerRepo;
import com.sk.service.CustomerService;

@Service
public class CustomerServicelogic implements CustomerService {

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	CartRepo cartRepo;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Cart cart = new Cart();
		customer.setCart(cart);
		cart.setCustomer(customer);
		Customer c = cRepo.save(customer);

		if (c != null) {
			return c;
		} else {
			throw new CustomerException("customer not added");
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Customer c = cRepo.findById(customer.getcId()).orElseThrow(() -> new CustomerException("Customer not found"));
		if (c != null) {
			cRepo.save(customer);
		}
		return c;
	}

	@Override
	public Customer remove(Integer customerId) throws CustomerException {
		Optional<Customer> opt = cRepo.findById(customerId);
		if (opt.isPresent()) {
			Customer c = opt.get();
			cRepo.delete(c);
			return c;
		} else {
			throw new CustomerException("Customer not found with cid - " + customerId);
		}

	}

	@Override
	public List<Customer> viewAllCustomer() throws CustomerException {
		List<Customer> customers = cRepo.findAll();
		if (customers.size() > 0) {
			return customers;
		} else {
			throw new CustomerException("customer not found");
		}
	}

}
