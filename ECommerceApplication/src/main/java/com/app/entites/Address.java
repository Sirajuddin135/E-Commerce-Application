package com.app.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;

	private String streetNo;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "addresses")
	private Set<User> users = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	private List<Order> orders = new ArrayList<>();

}
