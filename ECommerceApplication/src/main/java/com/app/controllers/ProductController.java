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

import com.app.entites.Product;
import com.app.payloads.ProductDTO;
import com.app.services.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "E-Commerce Application")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/categories/{categoryId}/products")
	public ResponseEntity<ProductDTO> addProduct(@PathVariable Long categoryId, @RequestBody Product product) {

		ProductDTO savedProduct = productService.addProduct(categoryId, product);

		return new ResponseEntity<ProductDTO>(savedProduct, HttpStatus.CREATED);
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAllProducts();

		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.FOUND);
	}

	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
		List<ProductDTO> products = productService.searchByCategory(categoryId);

		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.FOUND);
	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDTO> updateProductByCategory(@PathVariable Long productId,
			@RequestBody Product product) {
		ProductDTO updatedProduct = productService.updateProduct(productId, product);

		return new ResponseEntity<ProductDTO>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProductByCategory(@PathVariable Long productId) {
		String status = productService.deleteProduct(productId);

		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

}
