package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

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

@Transactional
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
		
		if(productFromDB == null) {
			
			CartItem cartItem = new CartItem();
			
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(quantity);
			cartItem.setProductPrice(product.getSpecialPrice());
			cartItem = cartItemRepo.save(cartItem);
			
			product.setQuantity(product.getQuantity() - quantity);
			
			cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));
			
			CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
			
			List<ProductDTO> productDTOs = cart.getProducts().stream().map(p -> modelMapper.map(p.getProduct(), ProductDTO.class))
					.collect(Collectors.toList());
			
			cartDTO.setProducts(productDTOs);
			
			return cartDTO;
			
		} else {
			
			throw new APIException("Product " + product.getProductName() + " already exists in the cart");
		}
	}

	@Override
	public List<CartDTO> getAllCarts() {
		List<Cart> carts = cartRepo.findAll();
		
		if(carts.size() == 0) {
			throw new APIException("No cart exists !!!");
		}
		
		List<CartDTO> cartDTOs = carts.stream().map(cart -> {
			CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
			
			List<ProductDTO> products = cart.getProducts().stream().map(p -> modelMapper.map(p.getProduct(), ProductDTO.class)).collect(Collectors.toList());
			
			cartDTO.setProducts(products);
			
			return cartDTO;
			
		}).collect(Collectors.toList());
		
		System.out.println(cartDTOs);
		
		return cartDTOs;
	}

	@Override
	public CartDTO getCart(Integer cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));
		
		List<ProductDTO> products = cart.getProducts().stream().map(p -> modelMapper.map(p.getProduct(), ProductDTO.class)).collect(Collectors.toList());
		
		System.out.println(products);
		
		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
		
		return cartDTO;
	}
	
	@Override
	public CartDTO updateProductInCart(Integer cartId, Integer productId, Integer quantity) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

		Product product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		if(product.getQuantity() == 0) {
			throw new APIException("Product: " + product.getProductName() + " not available !!!");
		}
		
		if(product.getQuantity() < quantity) {
			throw new APIException("Please, make an order of the " + product.getProductName() +" less than or equal to the quantity " + product.getQuantity() + ".");
		}
		
		CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(productId, cartId);
		
		double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());
		
		product.setQuantity(product.getQuantity() + cartItem.getQuantity() - quantity);
		
		cartItem.setProductPrice(product.getSpecialPrice());
		cartItem.setQuantity(quantity);
		
		cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * quantity));
		
		cartItem = cartItemRepo.save(cartItem);
		
		CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
		
		List<ProductDTO> productDTOs = cart.getProducts().stream().map(p -> modelMapper.map(p.getProduct(), ProductDTO.class))
				.collect(Collectors.toList());
		
		cartDTO.setProducts(productDTOs);
		
		return cartDTO;

	}

	@Override
	public String deleteProductFromCart(Integer cartId, Integer productId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));
		
		CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(productId, cartId);
		
		if(cartItem == null) {
			throw new ResourceNotFoundException("Product", "productId", productId);
		}
		
		cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));
		
		Product product = cartItem.getProduct();
		product.setQuantity(product.getQuantity() + cartItem.getQuantity());
		
		cartItemRepo.deleteCartItemByProductIdAndCartId(productId, cartId);
		
		return "Product " + cartItem.getProduct().getProductName() + " removed from the cart !!!";
	}
}
