package com.app.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
    
    //@NotNull(message = "Street Number cannot be left blank")
	private String streetNo;
	
    private String buildingName;
	
   // @NotNull(message = "City name cannot be left blank")
	private String city;
	
//    @NotNull(message = "State name cannot be left blank")
	private String state;
	
//    @NotNull(message = "Country name cannot be left blank")
	private String country;
	
//    @NotNull(message = "pincode cannot be left blank")
	private String pincode;
	
//	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="address")
	private List<Customer> Customer;
	
//	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="address")
	private List<Order> order;

}
