package com.app.service;

import java.util.List;

import com.app.exception.ProductException;
import com.app.model.Product;

public interface ProductService {

	public List<Product> viewAllProduct() throws ProductException;
	
	public Product addProduct(Product pdt) throws ProductException;
	
	public Product updateProduct(Product product) throws ProductException;
	
	public Product viewProduct(Integer Id) throws ProductException;
	
	public List<Product> viewProductByCategory(String cname) throws ProductException;
	
	public Product removeProduct(Integer Id) throws ProductException;
}
