package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Cart;
import com.app.entites.CartItem;
import com.app.entites.Order;
import com.app.entites.OrderItem;
import com.app.entites.Payment;
import com.app.entites.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.OrderDTO;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CartRepo;
import com.app.repositories.OrderItemRepo;
import com.app.repositories.OrderRepo;
import com.app.repositories.PaymentRepo;
import com.app.repositories.UserRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	public UserRepo userRepo;

	@Autowired
	public CartRepo cartRepo;

	@Autowired
	public OrderRepo orderRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	public OrderItemRepo orderItemRepo;

	@Autowired
	public CartItemRepo cartItemRepo;

	@Autowired
	public UserService userService;

	@Autowired
	public CartService cartService;

	@Autowired
	public ModelMapper modelMapper;

	@Override
	public OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod) {

		Cart cart = cartRepo.findCartByEmailAndCartId(emailId, cartId);

		if (cart == null) {
			throw new ResourceNotFoundException("Cart", "cartId", cartId);
		}

		Order order = new Order();

		order.setEmail(emailId);
		order.setOrderDate(LocalDate.now());
		
		order.setTotalAmount(cart.getTotalPrice());
		order.setOrderStatus("Order Accepted !");

		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setPaymentMethod(paymentMethod);
		
		payment = paymentRepo.save(payment);
		
		order.setPayment(payment);

		Order savedOrder = orderRepo.save(order);
		
		List<CartItem> cartItems = cart.getCartItems();
		
		if(cartItems.size() == 0) {
			throw new APIException("Cart is empty!!");
		}
		
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();

			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setDiscount(cartItem.getDiscount());
			orderItem.setOrderedProductPrice(cartItem.getProductPrice());
			orderItem.setOrder(savedOrder);
			
			orderItems.add(orderItem);
		}
		
		orderItemRepo.saveAll(orderItems);

		cart.getCartItems().forEach(item -> {
			int quantity = item.getQuantity();
			
			Product product = item.getProduct();
					
			cartService.deleteProductFromCart(cartId, item.getProduct().getProductId());
			
			product.setQuantity(product.getQuantity() - quantity);
		});

		OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);		
		
		return orderDTO;
	}

	@Override
	public List<OrderDTO> getOrdersByUser(String emailId) {
		List<Order> orders = orderRepo.findAllByEmail(emailId);
		
		List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
		
		if(orderDTOs.size() == 0) {
			throw new APIException("No orders placed yet by the user with email: " + emailId);
		}
		
		return orderDTOs;
	}
	
	@Override
	public OrderDTO getOrder(String emailId, Long orderId) {
		
		Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);

		if (order == null) {
			throw new ResourceNotFoundException("Order", "orderId", orderId);
		}

		return modelMapper.map(order, OrderDTO.class);
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<Order> orders = orderRepo.findAll();
		
		List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
		
		if(orderDTOs.size() == 0) {
			throw new APIException("No orders placed yet by the users");
		}
		
		return orderDTOs;
	}

	@Override
	public OrderDTO updateOrder(String emailId, Long orderId, String orderStatus) {
		
		Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);
		
		if (order == null) {
			throw new ResourceNotFoundException("Order", "orderId", orderId);
		}
		
		order.setOrderStatus(orderStatus);

		return modelMapper.map(order, OrderDTO.class);
	}

}
