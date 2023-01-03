package com.app.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productId;
	
	private String productName;
	private String image;
	private String description;
	private Integer quantity;
	private double price;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
	private List<Cart> carts = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
}
