package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Cart;
import com.app.entites.CartItem;
import com.app.entites.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.CartDTO;
import com.app.payloads.ProductDTO;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CartRepo;
import com.app.repositories.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public CartDTO addProductToCart(Integer cartId, Integer productId, Integer quantity) {
		
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

		Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
		
		if(product.getQuantity() == 0) {
			throw new APIException("Product: " + product.getProductName() + " not available !!!");
		}
		
		if(product.getQuantity() < quantity) {
			throw new APIException("Please, make an order of the " + product.getProductName() +" less than or equal to the quantity " + product.getQuantity() + ".");
		}
		
		Product productFromDB = cartItemRepo.findProductById(productId);
		
		CartItem cartItem = new CartItem();
		
		if(productFromDB == null) {
			
			cartItem.setCart(cart);
			
			cartItem.setProduct(product);
			
			product.getItems().add(cartItem);
			
			product.setQuantity(product.getQuantity() - quantity);
			
			cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));
			
			cart.getItems().add(cartItem);
			
			cartItem.setProductPrice(product.getSpecialPrice());
			
			cartItem = cartItemRepo.save(cartItem);
		} else {
			cartItem = cartItemRepo.findCartItemByProductId(productId);
			
			double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());
			
			cartItem.setProductPrice(product.getSpecialPrice());
			
			cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * quantity));
			
			product.setQuantity(product.getQuantity() + Math.abs(cartItem.getQuantity() - quantity));

		}
		
		cartItem.setQuantity(quantity);
		
		cartRepo.save(cart);
		
		return modelMapper.map(cart, CartDTO.class);
	}

}
