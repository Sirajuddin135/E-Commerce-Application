package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.ProductException;
import com.app.model.Product;
import com.app.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepo pRepo;
	
	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> allPdts = pRepo.findAll();
		
		if(allPdts.size() == 0) {
			throw new ProductException("No Products found in the store...");
		}
		
		return allPdts;
	}

	@Override
	public Product addProduct(Product pdt) throws ProductException {
		Product savedPdt = null;
		savedPdt = pRepo.save(pdt);
		if(savedPdt == null) {
			throw new ProductException("A product is already saved with this Id : " + pdt.getProductId());
		}
		
		return savedPdt;
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> tempPdt =  pRepo.findById(product.getProductId());
		
		if(tempPdt.isPresent()) {
			Product updatedPdt = pRepo.save(product);
			
			return updatedPdt;
		}else {
			throw new ProductException("No Product found to update with this Id: " + product.getProductId());
		}
		
	}

	@Override
	public Product viewProduct(Integer Id) throws ProductException {
		Optional<Product> tempPdt =  pRepo.findById(Id);
		
		if(tempPdt.isPresent()) {
			return tempPdt.get();
			
		}else {
			throw new ProductException("No Product found with this Id: " + Id);
		}
	}

	@Override
	public List<Product> viewProductByCategory(String cname) throws ProductException {
		List<Product> allPdts = pRepo.getProductByCategory(cname);
		
		if(allPdts.size() == 0) {
			throw new ProductException("No Product found with this category : " + cname);
		}
		
		return allPdts;
	}

	@Override
	public Product removeProduct(Integer Id) throws ProductException {
		Optional<Product> tempPdt =  pRepo.findById(Id);
		
		if(tempPdt.isPresent()) {
			Product delPdt = tempPdt.get();
			pRepo.delete(delPdt);
			
			return delPdt;
			
		}else {
			throw new ProductException("No Product found with this Id: " + Id);
		}
	}

}
