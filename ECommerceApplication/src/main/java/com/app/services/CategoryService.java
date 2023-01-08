package com.app.services;

import java.util.List;

import com.app.entites.Category;
import com.app.payloads.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(Category category);

	List<CategoryDTO> getCategories();

	CategoryDTO updateCategory(Category category, Integer categoryId);

	String deleteCategory(Integer categoryId);
}
