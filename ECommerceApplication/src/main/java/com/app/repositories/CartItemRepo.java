package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entites.CartItem;
import com.app.entites.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
	
	@Query("SELECT ci.product FROM CartItem ci WHERE ci.product.id = ?1")
	Product findProductById(Integer productId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.product.id = ?1")
	CartItem findCartItemByProductId(Integer productId);
}
