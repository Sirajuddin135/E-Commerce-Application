package com.app.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;

	private Double totalPrice = 0.0;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "cart")
	@JoinColumn(name = "user_id")
	private User user;

//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"), 
//		inverseJoinColumns = @JoinColumn(name = "product_id"))
//	private List<Product> products = new ArrayList<>();
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> products = new ArrayList<>();

}