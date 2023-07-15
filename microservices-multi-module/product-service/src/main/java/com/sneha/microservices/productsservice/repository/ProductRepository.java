package com.sneha.microservices.productsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneha.microservices.productsservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
