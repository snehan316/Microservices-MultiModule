package com.sneha.microservices.ordersservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneha.microservices.ordersservice.dto.OrderItemsDto;
import com.sneha.microservices.ordersservice.dto.OrderRequest;
import com.sneha.microservices.ordersservice.model.Order;
import com.sneha.microservices.ordersservice.model.OrderItems;
import com.sneha.microservices.ordersservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void createOrder(OrderRequest request) {
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderItems> orderItems = request.getOrderItems().stream().map(orderItemsDto -> mapToDto(orderItemsDto)).toList();
		order.setOrderItems(orderItems);
		
		orderRepository.save(order);
	}
	
	private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
		
		OrderItems orderItems = new OrderItems();
		orderItems.setPrice(orderItemsDto.getPrice());
		orderItems.setQuantity(orderItemsDto.getQuantity());
		orderItems.setSkuCode(orderItemsDto.getSkuCode());
		
		return orderItems;
	}
}
