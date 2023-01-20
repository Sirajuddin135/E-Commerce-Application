package com.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private Long categoryId;
	private String categoryName;
//	private List<ProductDTO> products = new ArrayList<>();
}
