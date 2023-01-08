package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private Integer productId;
	private String productName;
	private String image;
	private String description;
	private Integer quantity;
	private double price;
	private Integer rating;
//	private CategoryDTO category;

}
