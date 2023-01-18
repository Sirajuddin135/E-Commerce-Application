package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Cart;
import com.app.entites.CartItem;
import com.app.entites.Order;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.CartDTO;
import com.app.payloads.OrderDTO;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CartRepo;
import com.app.repositories.OrderRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	public CartRepo cartRepo;
	
	@Autowired
	public OrderRepo orderRepo;
	
	@Autowired
	public CartItemRepo cartItemRepo;
	
	@Autowired
	public CartService cartService;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public OrderDTO placeOrder(String emailId, Integer cartId) {
		
		Cart cart = cartItemRepo.findCartByEmailAndCartId(emailId, cartId);
		
		if(cart == null) {
			throw new ResourceNotFoundException("Cart", "cartId", cartId);
		}
		
		System.out.println(modelMapper.map(cart, CartDTO.class));
		
		Order order = new Order();
		
		order.setUser(cart.getUser());
		order.setOrderDate(LocalDate.now());
		
		List<CartItem> cartItems = cart.getProducts();
		List<CartItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem: cartItems) {
			CartItem orderItem = new CartItem();
			
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setDiscount(cartItem.getDiscount());
			orderItem.setProductPrice(cartItem.getProductPrice());
			orderItem.setOrder(order);
			
			orderItems.add(orderItem);
			
		}
		
		order.setOrderedProducts(orderItems);
		order.setTotalAmount(cart.getTotalPrice());
		order.setOrderStatus("Order Accepted !");
		
		Order savedOrder = orderRepo.save(order);
	
		cart.setTotalPrice(0.0);
		cart.getProducts().clear();
		
		cartRepo.save(cart);
		
		cartItemRepo.saveAll(cartItems);
		
		return modelMapper.map(savedOrder, OrderDTO.class);
	}

	@Override
	public Order getOrder(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deleteOrder(String email, Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
