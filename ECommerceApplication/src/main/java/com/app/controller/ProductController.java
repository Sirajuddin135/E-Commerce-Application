package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ProductException;
import com.app.model.Product;
import com.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pService;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> viewAllProductHandler() throws ProductException{
		
		List<Product> allPdts = pService.viewAllProduct();
		
		return new ResponseEntity<List<Product>>(allPdts, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProductHandler(@RequestBody Product pdt) throws ProductException {
		Product addedPdt = pService.addProduct(pdt);
		
		return new ResponseEntity<Product>(addedPdt, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product) throws ProductException{
		Product updated = pService.updateProduct(product);
		
		return new ResponseEntity<Product>(updated, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Product> viewProductHandler(@PathVariable Integer Id) throws ProductException{
		Product pdt = pService.viewProduct(Id);
		
		return new ResponseEntity<Product>(pdt, HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> viewProductByCategoryHandler(@RequestParam(value="category") String cname) throws ProductException{
		List<Product> allPdt = pService.viewProductByCategory(cname);
		
		return new ResponseEntity<List<Product>>(allPdt, HttpStatus.OK);
	}
	
	@DeleteMapping("/{Id}")
	public ResponseEntity<Product> removeProductHandler(@PathVariable Integer Id) throws ProductException {
		Product pdt = pService.removeProduct(Id);
		
		return new ResponseEntity<Product>(pdt, HttpStatus.OK);
	}
}
