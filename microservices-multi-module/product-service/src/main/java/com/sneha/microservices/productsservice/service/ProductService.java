package com.sneha.microservices.productsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneha.microservices.productsservice.dto.ProductRequest;
import com.sneha.microservices.productsservice.dto.ProductResponse;
import com.sneha.microservices.productsservice.model.Product;
import com.sneha.microservices.productsservice.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductResponse createProduct(ProductRequest request) {
		
		Product product = new Product(request.getName(), request.getDescription(), request.getPrice());
	
		productRepository.save(product);
		
		ProductResponse response = new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
		
		return response;
	}
	
	public List<ProductResponse> retriveAllProducts(){
		List<Product> products = productRepository.findAll();
		
		List<ProductResponse> response =  products.stream().map(product ->  mapToDto(product)).toList();
		
		return response;
	}
	
	private ProductResponse mapToDto(Product product) {
		ProductResponse response = new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
		return response;
	}
}
