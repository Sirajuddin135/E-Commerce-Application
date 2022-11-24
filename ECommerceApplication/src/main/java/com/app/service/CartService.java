package com.app.service;

import com.app.exception.CartException;
import com.app.model.Cart;

public interface CartService {
	
	public Cart addProductToCart(Cart cart) throws CartException;
	public Cart removeProductFromCart(Cart cart) throws CartException;
	public Cart plusProductQuantity(Cart cart) throws CartException;
	public Cart removeAllProducts(Cart cart) throws CartException;
	public Cart viewAllProducts(Cart cart) throws CartException;
	
}
