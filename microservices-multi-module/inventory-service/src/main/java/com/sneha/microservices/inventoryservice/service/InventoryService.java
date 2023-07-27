package com.sneha.microservices.inventoryservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneha.microservices.inventoryservice.dto.InventoryResponse;
import com.sneha.microservices.inventoryservice.model.Inventory;
import com.sneha.microservices.inventoryservice.repository.InventoryRepository;


@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		
	 List<Inventory> responses = inventoryRepository.findBySkuCodeIn(skuCode);
	 System.out.println("*".repeat(10) + "responses" + "*".repeat(10));
	 System.out.println(responses);
	 
	 List<InventoryResponse> responseDtoArray = new ArrayList<>() ;
	  
	 for(Inventory temp : responses) { 
		 responseDtoArray.add(mapToDto(temp));
	 }

	 return responseDtoArray;
		
				
	}
	
    private InventoryResponse mapToDto(Inventory invetory) {
		 
    	InventoryResponse inventoryResponse = new InventoryResponse();
    	inventoryResponse.setSkuCode(invetory.getSkuCode());
    	inventoryResponse.setInStock(invetory.getQuantity() > 0);
    	
    	return inventoryResponse;
	 }
}
