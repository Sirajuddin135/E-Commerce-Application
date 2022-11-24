package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.CartException;
import com.app.model.Cart;
import com.app.service.CartServiceImpl;

@RestController
public class CartController {
	
	@Autowired
	private CartServiceImpl cartService;
	
	@PostMapping("/addProductToCart")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.addProductToCart(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/removeProductFromCart")
	public ResponseEntity<Cart> removeProductFromCart(@RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.removeProductFromCart(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateProductQuantity")
	public ResponseEntity<Cart> updateProductQuantity(@RequestBody Cart cart) throws CartException{
		
		Cart nCart = cartService.plusProductQuantity(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/removeAllProducts")
	public ResponseEntity<Cart> removeAllProducts(@RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.removeAllProducts(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/viewAllProducts")
	public ResponseEntity<Cart> viewAllProducts(@RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.viewAllProducts(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
	}	
	
}
