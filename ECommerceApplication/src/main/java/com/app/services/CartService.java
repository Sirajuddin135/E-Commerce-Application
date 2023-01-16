package com.app.services;

import java.util.List;

import com.app.payloads.CartDTO;
import com.app.payloads.CartItemDTO;

public interface CartService {
	
	CartDTO addProductToCart(Integer cartId, Integer productId, Integer quantity);
	
	List<CartDTO> getAllCarts();
	
	CartDTO getCart(Integer cartId);
	
	CartDTO updateProductInCart(Integer cartId, Integer productId, Integer quantity);
//	CartItemDTO updateProductInCart(Integer cartId, Integer productId, Integer quantity);
	
	String deleteProductFromCart(Integer cartId, Integer productId);
}
