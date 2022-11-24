package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.OrderException;
import com.app.model.Order;

public interface OrderService {
	public Order addOrder(Order order, Integer customerId) throws OrderException;
	
	public Order updateOrder(Order order, Integer customerId) throws OrderException;
	
	public Order removeOrder(Integer orderId) throws OrderException;
	
	public Order viewOrder(Integer orderId) throws OrderException;
	
	public List<Order> viewAllOrders(LocalDate date) throws OrderException;
	
	public List<Order> viewAllOrdersByLocation(String loc) throws OrderException;
	
	public List<Order> viewAllOrdersByuserId(String userId) throws OrderException;

}
