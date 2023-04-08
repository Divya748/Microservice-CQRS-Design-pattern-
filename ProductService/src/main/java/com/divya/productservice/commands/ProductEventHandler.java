package com.divya.productservice.commands;


import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divya.productservice.core.data.ProductEntity;
import com.divya.productservice.core.data.ProductRepository;
import com.divya.productservice.core.events.ProductCreatedEvent;

@Component
public class ProductEventHandler {
	@Autowired
	private ProductRepository productRepository;
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		
	productRepository.save(productEntity);
		
	}

}
