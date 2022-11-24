package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.OrderException;
import com.app.model.Address;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.repository.AddressDao;
import com.app.repository.CurrentUserSessionRepo;
import com.app.repository.CustomerRepo;
import com.app.repository.OrderRepository;

@Service
public class OrderDaoImpl implements OrderService {

	@Autowired
	public OrderRepository orderRepo;

	@Autowired
	public CustomerRepo customerRepo;

	@Autowired
	public AddressDao addressDao;

	@Autowired
	public CurrentUserSessionRepo currentUserDao;

	@Override
	public Order addOrder(Order order, Integer customerId) throws OrderException {
		Optional<Customer> opt = customerRepo.findById(customerId);

		if (opt.isPresent()) {
			Customer c = opt.get();

			Address add = c.getAddress();

			if (add != null) {
				order.setCustomer(c);
				order.setAddress(add);

				Order ord = orderRepo.save(order);

				return ord;
			} else {
				throw new OrderException("Invalid customer address");
			}
		} else {
			throw new OrderException("Invalid customer details");
		}

	}

	@Override
	public Order updateOrder(Order order, Integer customerId) throws OrderException {
		Optional<Customer> opt = customerRepo.findById(customerId);

		if (opt.isPresent()) {
			Customer c = opt.get();

			Address add = c.getAddress();

			if (add != null) {
				
				Optional<Order> opt1 = orderRepo.findById(order.getOrderId());

				if (opt1.isPresent()) {
					order.setCustomer(c);
					order.setAddress(add);
					
					Order updatedOrder = orderRepo.save(order);

					return updatedOrder;
				} else {
					throw new OrderException("Invalid order details");
				}
			} else {
				throw new OrderException("Invalid address");
			}
		} else {
			throw new OrderException("Invalid customer details");
		}
	}

	@Override
	public Order removeOrder(Integer orderId) throws OrderException {
		Optional<Order> opt = orderRepo.findById(orderId);
		
		if(opt.isPresent()) {
			
			Order ord = opt.get();

			orderRepo.delete(ord);

			return ord;
		} else {
			throw new OrderException("Order details not found...");
		}
	}

	@Override
	public Order viewOrder(Integer orderId) throws OrderException {
		Optional<Order> opt = orderRepo.findById(orderId);

		if (opt.isPresent()) {
			Order ord = opt.get();

			return ord;
		} else {
			throw new OrderException("Order details not found...");
		}
	}

	@Override
	public List<Order> viewAllOrders(LocalDate date) throws OrderException {
		List<Order> listOfOrders = orderRepo.findByOrderDate(date);

		if (listOfOrders.size() == 0) {
			throw new OrderException("No order details found...");
		} else {
			return listOfOrders;
		}
	}

	@Override
	public List<Order> viewAllOrdersByLocation(String loc) throws OrderException {
		List<Order> listOfOrdersByLocation = orderRepo.getAllOrdersByLocation(loc);

		if (listOfOrdersByLocation.size() == 0) {
			throw new OrderException("No orders found from the location: " + loc);
		} else {
			return listOfOrdersByLocation;
		}
	}

	@Override
	public List<Order> viewAllOrdersByuserId(String userId) throws OrderException {
		Optional<Customer> opt = customerRepo.findById(Integer.parseInt(userId));

		if (!opt.isPresent()) {
			throw new OrderException("Customer not present...");
		}

		Customer c = opt.get();
		
		List<Order> orders = orderRepo.findAll();

		List<Order> listOfOrdersByUserId = new ArrayList<>();

		for (Order o : orders) {
			if(o.getCustomer().getCustomerId() != null) {
				if (o.getCustomer().getCustomerId() == c.getCustomerId()) {
					listOfOrdersByUserId.add(o);
				}
			}
		}

		if (listOfOrdersByUserId.size() == 0) {
			throw new OrderException("No orders found from the user id: " + userId);
		} else {
			return listOfOrdersByUserId;
		}
	}
}
