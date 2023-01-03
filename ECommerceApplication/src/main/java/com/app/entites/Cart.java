package com.app.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@Column(name = "cart_id")
	private Integer cartId;

	private Double totalPrice;
	private String paymentType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"), 
		inverseJoinColumns = @JoinColumn(name = "product_id"))
//		uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id, product_id" }), 
//		indexes = {@Index(name = "IDX_CART_PRODUCT", columnList = "cart_id, product_id")})
	private List<Product> products = new ArrayList<>();

}