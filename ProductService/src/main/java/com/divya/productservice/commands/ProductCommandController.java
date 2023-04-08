package com.divya.productservice.commands;

import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductCommandController {
	
	@Autowired
	CommandGateway commandGateway;

	@PostMapping
	public String craeteProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
		
		CreateProductCommand createProductCommand=CreateProductCommand.builder()
		.title(createProductRestModel.getTitle())
		.price(createProductRestModel.getPrice())
		.quantity(createProductRestModel.getQuantity())
		.productId(UUID.randomUUID().toString())
		.build();
		
	String result;
	try {
		result = commandGateway.sendAndWait(createProductCommand);
	} catch (Exception e) {
		result = e.getLocalizedMessage();
	}
	
		
		return result;
	}
	
	/*
	 * @GetMapping public String getProduct() { return "Get Method"; }
	 * 
	 * @PutMapping public String updateProduct() { return "update Method"; }
	 * 
	 * @DeleteMapping public String deleteProduct() { return "delete Method"; }
	 */
}
