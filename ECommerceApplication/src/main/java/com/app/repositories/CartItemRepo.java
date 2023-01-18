package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entites.Cart;
import com.app.entites.CartItem;
import com.app.entites.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
	
	@Query("SELECT ci.product FROM CartItem ci WHERE ci.product.id = ?1")
	Product findProductById(Integer productId);	
	
	@Query("SELECT ci.cart FROM CartItem ci WHERE ci.product.id = ?1")
	List<Cart> findCartByProductId(Integer productId);
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
	CartItem findCartItemByProductIdAndCartId(Integer cartId, Integer productId);
	
	@Query("SELECT ci.cart FROM CartItem ci WHERE ci.cart.user.email = ?1 AND ci.cart.id = ?2")
	Cart findCartByEmailAndCartId(String email, Integer cartId);
	
	@Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
    void deleteCartItemByProductIdAndCartId(Integer productId, Integer cartId);
}
