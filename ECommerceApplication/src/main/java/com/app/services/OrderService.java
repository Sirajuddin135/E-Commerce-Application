package com.app.services;

import java.util.List;

import com.app.payloads.OrderDTO;

public interface OrderService {
	OrderDTO placeOrder(String emailId, Integer cartId);
	
	OrderDTO getOrder(String emailId, Integer orderId);
	
	List<OrderDTO> getAllOrders(String emailId);
	
	String deleteOrder(String email, Integer orderId);
	
}
