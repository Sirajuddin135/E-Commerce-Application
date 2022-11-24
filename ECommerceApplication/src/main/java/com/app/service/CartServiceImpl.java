package com.app.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.CartException;
import com.app.model.Cart;
import com.app.repository.CartRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public Cart addProductToCart(Cart cart) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		
		if(opt.isPresent()) {
//			cart.getProducts().add(product);
			cartRepo.save(cart);
//		} else {
//			cart.getProducts().add(product);
//			return cartRepo.save(cart);
		}	 
		
		return cart;
	}

	@Override
	public Cart removeProductFromCart(Cart cart) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			cartRepo.delete(cart);
			
			return cart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}

	@Override
	public Cart plusProductQuantity(Cart cart) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			Cart nCart = opt.get();
			
			cartRepo.save(nCart);
			return nCart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}


	@Override
	public Cart removeAllProducts(Cart cart) throws CartException {
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			Cart nCart = opt.get();
			nCart.getProducts().clear();
			return nCart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}

	@Override
	public Cart viewAllProducts(Cart cart) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			Cart nCart = opt.get();
			return nCart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}

}