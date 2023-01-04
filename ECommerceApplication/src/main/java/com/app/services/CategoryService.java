package com.app.services;

import java.util.List;

import com.app.entites.Category;
import com.app.entites.Product;

public interface CategoryService {

	Category addProduct(String categoryName, Product product);
	
	List<Product> searchByCategory(String categoryName);

}
