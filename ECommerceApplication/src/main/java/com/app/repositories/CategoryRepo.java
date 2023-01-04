package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByCategoryName(String categoryName);

}
