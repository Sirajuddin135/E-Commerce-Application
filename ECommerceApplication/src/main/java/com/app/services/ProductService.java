package com.app.services;

import java.util.List;

import com.app.entites.Product;
import com.app.payloads.ProductDTO;

public interface ProductService {

	ProductDTO addProduct(Long categoryId, Product product);

	List<ProductDTO> getAllProducts();

	List<ProductDTO> searchByCategory(Long categoryId);

	ProductDTO updateProduct(Long productId, Product product);

	String deleteProduct(Long productId);
}
