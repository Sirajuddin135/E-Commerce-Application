package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.CartDTO;
import com.app.payloads.CartItemDTO;
import com.app.services.CartService;

@RestController
@RequestMapping("/api/user/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/{cartId}/product/{productId}/quantity/{quantity}")
	public ResponseEntity<CartDTO> addProductToCart(@PathVariable Integer cartId, @PathVariable Integer productId, @PathVariable Integer quantity) {
		CartDTO cartDTO = cartService.addProductToCart(cartId, productId, quantity);
		
		return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CartDTO>> getCarts() {
		List<CartDTO> cartDTOs = cartService.getAllCarts();
		
		return new ResponseEntity<List<CartDTO>>(cartDTOs, HttpStatus.FOUND);
	}
	
	@GetMapping("/{cartId}")
	public ResponseEntity<CartDTO> getCartById(@PathVariable Integer cartId) {
		CartDTO cartDTO = cartService.getCart(cartId);
		
		return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.FOUND);
	}
	
	@PutMapping("/{cartId}/product/{productId}/quantity/{quantity}")
	public ResponseEntity<CartDTO> updateCartProduct(@PathVariable Integer cartId, @PathVariable Integer productId, @PathVariable Integer quantity) {
		CartDTO cartDTO = cartService.updateProductInCart(cartId, productId, quantity);
		
		return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{cartId}/product/{productId}")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
		String status = cartService.deleteProductFromCart(cartId, productId);
		
		return new ResponseEntity<String>(status, HttpStatus.FOUND);
	}
}
