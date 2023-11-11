package com.sk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sk.model.Customer;
import com.sk.model.Orders;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query("select c.orders from Customer c where c.cId=?1")
	public List<Orders> getAllOrderByCid(Integer cId);

	public Customer findByEmail(String email);
}
