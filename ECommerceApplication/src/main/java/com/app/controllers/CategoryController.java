package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.Category;
import com.app.entites.Product;
import com.app.services.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{categoryName}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
		List<Product> products = categoryService.searchByCategory(categoryName);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.FOUND);
	}
	
	@PostMapping("/{categoryName}/product")
	public ResponseEntity<Category> addProduct(@PathVariable String categoryName, @RequestBody Product product) {
		Category savedCategory = categoryService.addProduct(categoryName, product);
		
		return new ResponseEntity<Category>(savedCategory, HttpStatus.FOUND);
	}
	
}
