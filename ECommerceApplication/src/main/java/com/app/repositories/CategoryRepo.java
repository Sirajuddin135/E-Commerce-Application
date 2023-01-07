package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entites.Category;
import com.app.entites.Product;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	Category findByCategoryName(String categoryName);

	@Query("SELECT p FROM Category c JOIN c.products p WHERE c.id = ?1 AND p.id = ?2")
	Product findByProducts(Integer categoryId, Integer productId);
}
