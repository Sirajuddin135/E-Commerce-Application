package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.OrderDTO;
import com.app.services.OrderService;

@RestController
@RequestMapping("/api/users/")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@PostMapping("/{emailId}/carts/{cartId}/orders")
	public ResponseEntity<OrderDTO> orderProducts(@PathVariable String emailId, @PathVariable Integer cartId) {
		OrderDTO order = orderService.placeOrder(emailId, cartId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/{emailId}/orders/{orderId}")
	public ResponseEntity<OrderDTO> getProduct(@PathVariable String emailId, @PathVariable Integer orderId) {
		OrderDTO order = orderService.getOrder(emailId, orderId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
}
