package com.sneha.microservices.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sneha.microservices.inventoryservice.dto.InventoryResponse;
import com.sneha.microservices.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/inventory")
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}
}
