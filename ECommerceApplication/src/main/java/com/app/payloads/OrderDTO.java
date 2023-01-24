package com.app.payloads;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.entites.OrderItem;
import com.app.entites.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Long orderId;
//	private User user;
	private List<OrderItem> orderItems = new ArrayList<>();
	private LocalDate orderDate;
	private Payment payment;
	private Double totalAmount;
	private String orderStatus;
	private String paymentMethod;

}
