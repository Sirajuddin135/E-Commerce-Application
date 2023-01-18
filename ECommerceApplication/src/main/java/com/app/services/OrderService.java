package com.app.services;

import java.util.List;

import com.app.entites.Order;
import com.app.payloads.OrderDTO;

public interface OrderService {
	OrderDTO placeOrder(String emailId, Integer cartId);
	
	Order getOrder(String emailId);
	
	List<Order> getAllOrders(String emailId);
	
	Order deleteOrder(String email, Integer orderId);
	
	
}
