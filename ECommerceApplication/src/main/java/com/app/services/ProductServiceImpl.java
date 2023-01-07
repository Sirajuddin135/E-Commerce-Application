package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Category;
import com.app.entites.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.ProductDTO;
import com.app.repositories.CategoryRepo;
import com.app.repositories.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO addProduct(Integer categoryId, Product product) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		boolean flag = true;
		
		List<Product> products = category.getProducts();
		
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getProductName().equals(product.getProductName()) && 
					products.get(i).getDescription().equals(product.getDescription())) {
				
				flag = false;
				break;
			}
		}
		
		if(flag) {
			product.setCategory(category);
			
			Product savedProduct = productRepo.save(product);
			
			return modelMapper.map(savedProduct, ProductDTO.class);
		}  else {
			throw new APIException("Product already exists !!!");
		}
	}
	
	@Override
	public List<ProductDTO> searchByCategory(Integer categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		List<Product> products = category.getProducts();
		
		if(products.size() == 0) {
			throw new APIException("Category " + categoryId + " doesn't contain any products !!!");
		}
		
		List<ProductDTO> productDTOs = products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
		
		return productDTOs;
	}

	@Override
	public ProductDTO updateProduct(Integer categoryId, Integer productId, Product product) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Product p = categoryRepo.findByProducts(categoryId, productId);
		
		if(p == null) {
			throw new APIException("Product not found with productId: " + productId + " in a category with categoryId: " + categoryId);
		}
		
		product.setProductId(productId);
		product.setCategory(category);
		
		Product savedProduct = productRepo.save(product);
		
		return modelMapper.map(savedProduct, ProductDTO.class);	
	}

	@Override
	public String deleteProduct(Integer categoryId,Integer productId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Product product = categoryRepo.findByProducts(categoryId, productId);
		
		if(product == null) {
			throw new APIException("Product not found with productId: " + productId + " in a category with categoryId: " + categoryId);
		}
		
		category.getProducts().remove(product);
		
		productRepo.delete(product);
		
		categoryRepo.save(category);
		
		return "Product with productId: " + productId + " deleted successfully from category with categoryId: " + categoryId;
	}

}
