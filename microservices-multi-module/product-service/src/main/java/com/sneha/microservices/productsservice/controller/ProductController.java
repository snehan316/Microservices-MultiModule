package com.sneha.microservices.productsservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sneha.microservices.productsservice.dto.ProductRequest;
import com.sneha.microservices.productsservice.dto.ProductResponse;
import com.sneha.microservices.productsservice.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest request) {
		return service.createProduct(request);
	}
	
	@GetMapping("/products")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> retrieveAllProducts(){
		return service.retriveAllProducts();
	}
}

