package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Category;
import com.app.entites.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.repositories.CategoryRepo;
import com.app.repositories.ProductRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public List<Product> searchByCategory(String categoryName) {
		
		Category category = categoryRepo.findByCategoryName(categoryName).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryType", categoryName));

		List<Product> products = category.getProducts();
		
		if(products.size() == 0) {
			throw new APIException("Category " + categoryName + " doesn't contain any products !!!");
		}
		
		return products;
	}

	@Override
	public Category addProduct(String categoryName, Product product) {
		Optional<Category> opt = categoryRepo.findByCategoryName(categoryName);
		
		Category category = null;
		
		if(opt.isEmpty()) {
			
			category = new Category(categoryName, List.of(product));
		} else {
			category = opt.get();
			category.getProducts().add(product);
		}
		
		Category savedCategory = categoryRepo.save(category);
		
		return savedCategory;
	}
	
}
