package com.app.services;

import org.springframework.web.multipart.MultipartFile;

import com.app.entites.Product;
import com.app.payloads.ProductDTO;
import com.app.payloads.ProductResponse;

public interface ProductService {

	ProductDTO addProduct(Long categoryId, Product product, MultipartFile image);

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	ProductDTO updateProduct(Long productId, Product product, MultipartFile image);

	String deleteProduct(Long productId);
}
