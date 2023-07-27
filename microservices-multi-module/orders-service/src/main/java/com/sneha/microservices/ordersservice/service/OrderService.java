package com.sneha.microservices.ordersservice.service;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.sneha.microservices.ordersservice.dto.InventoryResponse;
import com.sneha.microservices.ordersservice.dto.OrderLineItemsDto;
import com.sneha.microservices.ordersservice.dto.OrderRequest;
import com.sneha.microservices.ordersservice.model.Order;
import com.sneha.microservices.ordersservice.model.OrderLineItems;
import com.sneha.microservices.ordersservice.repository.OrderRepository;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@Service
@Transactional
public class OrderService {

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private WebClient.Builder webClientBuilder;
    

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock
        
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();
            
            System.out.println(Arrays.toString(inventoryResponseArray));
            
            boolean allProductsInStock = false;
            
//            for(InventoryResponse temp: inventoryResponseArray) {
//            	if(temp.isInStock()) {
//            		allProductsInStock = true;
//            	}
//            }
            
            allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);

            System.out.println(allProductsInStock);
            if (allProductsInStock) {
                orderRepository.save(order);
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
