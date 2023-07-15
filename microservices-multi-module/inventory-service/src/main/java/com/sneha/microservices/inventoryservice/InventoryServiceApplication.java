package com.sneha.microservices.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sneha.microservices.inventoryservice.model.Inventory;
import com.sneha.microservices.inventoryservice.repository.InventoryRepository;
import com.sneha.microservices.inventoryservice.service.InventoryService;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner load(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone13");
			inventory1.setQuantity(2);
			
			Inventory inventory2 = new Inventory();

			inventory2.setSkuCode("Dell Monitor");
			inventory2.setQuantity(10);
			
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			
		};
	}

}
