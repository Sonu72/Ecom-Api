package com.sk.service;

import java.util.List;

import com.sk.exception.CartException;
import com.sk.exception.CustomerException;
import com.sk.exception.OrderException;
import com.sk.model.Orders;

public interface OrderService {

	public Orders addOrder(Integer cid) throws OrderException, CustomerException, CartException;

	public Orders updateOrder(Orders order) throws OrderException;

	public Orders viewOrder(Integer orderId) throws OrderException;

	public List<Orders> viewAllOrder() throws OrderException;

	public List<Orders> viewAllOrdersByUserId(Integer userId) throws OrderException;

}
