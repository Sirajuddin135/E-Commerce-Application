package com.app.entites;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"addressId"})
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;

	private String street;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;

	@ManyToMany(mappedBy = "addresses")
	private Set<User> users = new HashSet<>();
	
	public Address(String country, String state, String city, String pincode, String street, String buildingName) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.street = street;
		this.buildingName = buildingName;
	}

}
