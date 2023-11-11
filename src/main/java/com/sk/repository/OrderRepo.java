package com.sk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
