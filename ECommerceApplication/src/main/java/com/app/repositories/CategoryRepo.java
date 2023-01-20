package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findByCategoryName(String categoryName);

}
