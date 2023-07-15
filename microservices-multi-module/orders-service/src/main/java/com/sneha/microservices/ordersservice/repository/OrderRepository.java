package com.sneha.microservices.ordersservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneha.microservices.ordersservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
