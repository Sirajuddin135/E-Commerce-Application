package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entites.Cart;
import com.app.entites.Category;
import com.app.entites.Product;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.CartDTO;
import com.app.payloads.ProductDTO;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CategoryRepo;
import com.app.repositories.ProductRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO addProduct(Integer categoryId, Product product) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		boolean isProductNotPresent = true;

		List<Product> products = category.getProducts();

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductName().equals(product.getProductName())
					&& products.get(i).getDescription().equals(product.getDescription())) {

				isProductNotPresent = false;
				break;
			}
		}

		if (isProductNotPresent) {
			product.setCategory(category);

			double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
			product.setSpecialPrice(specialPrice);

			Product savedProduct = productRepo.save(product);

			return modelMapper.map(savedProduct, ProductDTO.class);
		} else {
			throw new APIException("Product already exists !!!");
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepo.findAll();

		List<ProductDTO> productDTOs = products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());

		return productDTOs;
	}

	@Override
	public List<ProductDTO> searchByCategory(Integer categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		List<Product> products = category.getProducts();

		if (products.size() == 0) {
			throw new APIException("Category " + categoryId + " doesn't contain any products !!!");
		}

		List<ProductDTO> productDTOs = products.stream().map(p -> modelMapper.map(p, ProductDTO.class))
				.collect(Collectors.toList());

		return productDTOs;
	}

	@Override
	public ProductDTO updateProduct(Integer productId, Product product) {
		Product productFromDB = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		if (productFromDB == null) {
			throw new APIException("Product not found with productId: " + productId);
		}

		product.setProductId(productId);
		product.setCategory(productFromDB.getCategory());

		double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
		product.setSpecialPrice(specialPrice);

		Product savedProduct = productRepo.save(product);
		
//		List<CartDTO> carts = cartService.getAllCarts();
		
		List<Cart> carts = cartItemRepo.findCartByProductId(productId);
		
		List<CartDTO> cartDTOs = carts.stream().map(cart -> {
			CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

			List<ProductDTO> products = cart.getProducts().stream()
					.map(p -> modelMapper.map(p.getProduct(), ProductDTO.class)).collect(Collectors.toList());

			cartDTO.setProducts(products);

			return cartDTO;

		}).collect(Collectors.toList());

		cartDTOs.forEach(cart -> cartService.updateProductInCarts(cart.getCartId(), productId));
		
		return modelMapper.map(savedProduct, ProductDTO.class);
	}

	@Override
	public String deleteProduct(Integer productId) {

		Product p = productRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

		List<CartDTO> carts = cartService.getAllCarts();

		carts.forEach(cart -> cartService.deleteProductFromCart(cart.getCartId(), productId));

		productRepo.delete(p);

		return "Product with productId: " + productId + " deleted successfully !!!";
	}

}
