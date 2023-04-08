package com.divya.productservice.commands;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.divya.productservice.core.events.ProductCreatedEvent;



@Aggregate
public class ProductAggregrate {

	@AggregateIdentifier
	private  String productId;
	private String title;
	private BigDecimal price;
	private Integer quantity;
	
	public ProductAggregrate() {

	}

	@CommandHandler
	public ProductAggregrate(CreateProductCommand createProductCommand) throws IllegalAccessException {

		//validations
		if (createProductCommand.getTitle() == null 
				|| createProductCommand.getTitle().isBlank()) {
			throw new IllegalAccessException("title can't be empty");
		}
		
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
		
		AggregateLifecycle.apply(productCreatedEvent);
	}
	
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent ) {
		this.productId = productCreatedEvent.getProductId();
		this.title = productCreatedEvent.getTitle();
		this.price = productCreatedEvent.getPrice();
		this.quantity = productCreatedEvent.getQuantity();
	}

	

	
}
