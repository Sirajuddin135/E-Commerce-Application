package com.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotNull(message = "firstName required to fill")
	private String firstName;
	
	@NotNull(message = "lastname required to fill")
	private String lastname;
	
	@NotNull(message = "mobileNumber required to fill")
	private String mobileNumber;
	
	@NotNull(message = "email required to fill")
	private String email;
	
	@NotNull(message = "password required to fill")
	private String password;
	
	@NotNull(message = "role required to fill")
	private String role;
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	
//	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Order> order = new ArrayList<>();
	
//	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
}

