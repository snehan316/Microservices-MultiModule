package com.sneha.microservices.ordersservice.exception;


public class ProductNotInStockException extends RuntimeException {

	public ProductNotInStockException(String message) {
		super(message);
	}
}
