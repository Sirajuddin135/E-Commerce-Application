package com.app.services;

import java.util.List;

import com.app.payloads.OrderDTO;

public interface OrderService {
	OrderDTO placeOrder(String emailId, Long cartId);
	
	OrderDTO getOrder(String emailId, Long orderId);
	
	List<OrderDTO> getAllOrders(String emailId);
	
	String deleteOrder(String email, Long orderId);
	
}
