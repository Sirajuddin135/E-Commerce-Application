package com.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	private int productId;
	
	private String productName;
	private double price;
	private String color;
	private String dimension;
	private String specification;
	private String manufacturer;
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
	private Cart cart;
	
	@ManyToMany(cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<Order> order;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
}
