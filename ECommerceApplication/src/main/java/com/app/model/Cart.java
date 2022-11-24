package com.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	
//	@NotNull(message = "Customer details cannot be left blank")
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
//	@NotNull(message = "Please select atleast one product")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<Product> products;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Product product;
}