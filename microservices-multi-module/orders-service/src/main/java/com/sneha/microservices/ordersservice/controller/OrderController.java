package com.sneha.microservices.ordersservice.controller;


import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sneha.microservices.ordersservice.dto.OrderRequest;
import com.sneha.microservices.ordersservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
    	orderService.placeOrder(orderRequest);
        return "Order placed Successfully";
    }
    
    //should have the same method return type as placeOrder method (where circuit-breaker is added)
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runTimeException) {
        return "Something went wrong, please try again after sometime";
    }

}
