package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.OrderDTO;
import com.app.services.OrderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@PostMapping("/public/users/{emailId}/carts/{cartId}/payments/{paymentMethod}/order")
	public ResponseEntity<OrderDTO> orderProducts(@PathVariable String emailId, @PathVariable Long cartId, @PathVariable String paymentMethod) {
		OrderDTO order = orderService.placeOrder(emailId, cartId, paymentMethod);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}

	@GetMapping("/admin/orders")
	public ResponseEntity<List<OrderDTO>> getAllOrdersByUser() {
		List<OrderDTO> orders = orderService.getAllOrders();
		
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.FOUND);
	}
	
	@GetMapping("public/users/{emailId}/orders")
	public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String emailId) {
		List<OrderDTO> orders = orderService.getOrdersByUser(emailId);
		
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.FOUND);
	}
	
	@GetMapping("public/users/{emailId}/orders/{orderId}")
	public ResponseEntity<OrderDTO> getOrderByUser(@PathVariable String emailId, @PathVariable Long orderId) {
		OrderDTO order = orderService.getOrder(emailId, orderId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.FOUND);
	}
	
	@PutMapping("admin/users/{emailId}/orders/{orderId}/orderStatus/{orderStatus}")
	public ResponseEntity<OrderDTO> updateOrderByUser(@PathVariable String emailId, @PathVariable Long orderId, @PathVariable String orderStatus) {
		OrderDTO order = orderService.updateOrder(emailId, orderId, orderStatus);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.OK);
	}

}
