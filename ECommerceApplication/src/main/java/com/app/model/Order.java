package com.app.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
//	@NotNull(message = "{date.invalid}")
	private LocalDate orderDate;
	
//	@NotNull(message = "{status.invalid}")
	private String orderStatus;
	
//	@NotNull(message = "{customer.invalid}")
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
//	@NotNull(message = "{product.invalid}")
//	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<Product> productList;
	
//	@NotNull(message = "{address.invalid}")
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;	
}