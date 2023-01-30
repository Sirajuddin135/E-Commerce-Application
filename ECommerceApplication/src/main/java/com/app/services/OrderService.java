package com.app.services;

import java.util.List;

import com.app.payloads.OrderDTO;

public interface OrderService {
	
	OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod);
	
	List<OrderDTO> getOrdersByUser(String emailId);
	
	OrderDTO getOrder(String emailId, Long orderId);
	
	List<OrderDTO> getAllOrders();
	
	OrderDTO updateOrder(String emailId, Long orderId, String orderStatus);
}
