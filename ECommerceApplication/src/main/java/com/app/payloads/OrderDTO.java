package com.app.payloads;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	private UserDTO user;
	private LocalDate orderDate;
	private List<CartItemDTO> cartItems = new ArrayList<>();
	private Double totalAmount;
	private String orderStatus;

}
