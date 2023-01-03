package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
