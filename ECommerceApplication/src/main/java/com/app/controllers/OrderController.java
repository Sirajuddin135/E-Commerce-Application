package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.OrderDTO;
import com.app.services.OrderService;

@RestController
@RequestMapping("/api/user")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@PostMapping("/{emailId}/carts/{cartId}/orders/payment/{paymentMethod}")
	public ResponseEntity<OrderDTO> orderProducts(@PathVariable String emailId, @PathVariable Long cartId, @PathVariable String paymentMethod) {
		OrderDTO order = orderService.placeOrder(emailId, cartId, paymentMethod);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/{emailId}/orders/{orderId}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable String emailId, @PathVariable Long orderId) {
		OrderDTO order = orderService.getOrder(emailId, orderId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/{emailId}/orders")
	public ResponseEntity<List<OrderDTO>> getAllOrders(@PathVariable String emailId) {
		List<OrderDTO> orders = orderService.getAllOrders(emailId);
		
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.CREATED);
	}
}
