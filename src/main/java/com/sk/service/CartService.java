package com.sk.service;

import com.sk.exception.CartException;
import com.sk.exception.CustomerException;
import com.sk.exception.ProductException;
import com.sk.model.Cart;

public interface CartService {

	public Cart addProductToCart(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeProductFromCart(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart removeAllProduct(Integer customerId) throws CartException, CustomerException;

	public Cart increaseProductQuantity(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

	public Cart decreaseProductQuantity(Integer customerId, Integer productId)
			throws CartException, CustomerException, ProductException;

}
