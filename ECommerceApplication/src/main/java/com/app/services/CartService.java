package com.app.services;

import java.util.List;

import com.app.payloads.CartDTO;

public interface CartService {
	
	CartDTO addProductToCart(Integer cartId, Integer productId, Integer quantity);
	
	List<CartDTO> getAllCarts();
	
	CartDTO getCart(String emailId, Integer cartId);
	
	CartDTO updateProductQuantityInCart(Integer cartId, Integer productId, Integer quantity);
	
	void updateProductInCarts(Integer cartId, Integer productId);
	
	String deleteProductFromCart(Integer cartId, Integer productId);
}
