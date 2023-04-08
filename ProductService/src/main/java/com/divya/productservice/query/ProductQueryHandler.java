package com.divya.productservice.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.divya.productservice.core.data.ProductEntity;
import com.divya.productservice.core.data.ProductRepository;

@Component
public class ProductQueryHandler {

	@Autowired
	private ProductRepository productRepository;
	
	@QueryHandler
	public List<ProductRestModel> getAllProducts(FindProductsQuery findProductsQuery) {
		
		List<ProductRestModel> products = new ArrayList<>();
		
		List<ProductEntity> listOfProducts = productRepository.findAll();
		for (ProductEntity productEntity : listOfProducts) {
			ProductRestModel productRestModel = new ProductRestModel();
			BeanUtils.copyProperties(productEntity, productRestModel);
			products.add(productRestModel);
		}
		return products;
	}
}
