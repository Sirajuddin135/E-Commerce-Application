package com.app.services;

import com.app.payloads.CartDTO;

public interface CartService {
	
	CartDTO addProductToCart(Integer cartId, Integer productId, Integer quantity);
}
