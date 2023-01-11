package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	private Integer addressId;
	private String street;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
