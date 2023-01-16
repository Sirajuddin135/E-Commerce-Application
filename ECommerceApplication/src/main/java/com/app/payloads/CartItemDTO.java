package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
	private Integer cartItemId;
	private CartDTO cart;
	private ProductDTO product;
	private Integer quantity;
	private double productPrice;
}
