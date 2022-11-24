package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.OrderException;
import com.app.model.Order;
import com.app.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	public OrderService os;

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrderHandler(@RequestBody Order order, @RequestParam Integer customerId) throws OrderException {
		Order addedOrder = os.addOrder(order, customerId);

		return new ResponseEntity<Order>(addedOrder, HttpStatus.OK);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<Order> updateOrderHandler(@RequestBody Order order, @RequestParam Integer customerId) throws OrderException {
		Order updatedOrder = os.updateOrder(order, customerId);

		return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<Order> removeOrderHandler(@PathVariable Integer orderId) throws OrderException {
		Order deletedOrder = os.removeOrder(orderId);

		return new ResponseEntity<Order>(deletedOrder, HttpStatus.OK);
	}

	@GetMapping("/viewOrder/{orderId}")
	public ResponseEntity<Order> viewOrderHandler(@PathVariable Integer orderId) throws OrderException {
		Order ord = os.viewOrder(orderId);

		return new ResponseEntity<Order>(ord, HttpStatus.OK);
	}

	@GetMapping("/viewOrders/{date}")
	public ResponseEntity<List<Order>> viewAllOrdersHandler(@PathVariable("date") String date) throws OrderException {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		  LocalDate date1 = LocalDate.parse(date, formatter);
		
		List<Order> listOfOrders = os.viewAllOrders(date1);

		return new ResponseEntity<List<Order>>(listOfOrders, HttpStatus.OK);
	}

	@GetMapping("/viewOrdersByLocation/{loc}")
	public ResponseEntity<List<Order>> viewAllOrdersByLocationHandler(@PathVariable("loc") String loc) throws OrderException {
		List<Order> ordersListByLocation = os.viewAllOrdersByLocation(loc);

		return new ResponseEntity<List<Order>>(ordersListByLocation, HttpStatus.OK);
	}

	@GetMapping("/viewOrdersByUserId/{userId}")
	public ResponseEntity<List<Order>> viewAllOrdersByuserIdHandler(@PathVariable String userId) throws OrderException {
		List<Order> ordersListByUserId = os.viewAllOrdersByuserId(userId);

		return new ResponseEntity<List<Order>>(ordersListByUserId, HttpStatus.OK);
	}
}
