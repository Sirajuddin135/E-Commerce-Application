package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("select p from Product p where category IN(select c from Category c where c.categoryName=?1)")
	public List<Product> getProductByCategory(String cName);
}
