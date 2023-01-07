package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.Category;
import com.app.payloads.CategoryDTO;
import com.app.services.CategoryService;

@RestController
@RequestMapping("/api/user/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
		CategoryDTO savedCategoryDTO = categoryService.createCategory(category);
		
		return new ResponseEntity<CategoryDTO>(savedCategoryDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> categories = categoryService.getCategories();
		
		return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody Category category, @PathVariable Integer categoryId) {
		CategoryDTO categoryDTO = categoryService.updateCategory(category, categoryId);
		
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
		String status = categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}
	
}
