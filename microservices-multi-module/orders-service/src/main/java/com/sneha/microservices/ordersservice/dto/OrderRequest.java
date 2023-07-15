package com.sneha.microservices.ordersservice.dto;

import java.util.List;

import com.sneha.microservices.ordersservice.model.OrderItems;

public class OrderRequest {
	private List<OrderItemsDto> orderItems;
	
	
	public OrderRequest() {
		super();
	}

	public OrderRequest(List<OrderItemsDto> orderItems) {
		super();
		this.orderItems = orderItems;
	}


	public List<OrderItemsDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemsDto> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	
}
