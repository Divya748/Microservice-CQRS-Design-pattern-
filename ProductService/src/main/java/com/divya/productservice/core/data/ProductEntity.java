package com.divya.productservice.core.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -227264951080660124L;
	
	@Id
	private String productId;
	

	private String title;
	private BigDecimal price;
	private Integer quantity;
}
