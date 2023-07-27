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

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
    	orderService.placeOrder(orderRequest);
        return "Order placed Successfully";
    }

}
