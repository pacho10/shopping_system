package com.example.shoppingapplication.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.shoppingapplication.dto.ProductDto;
import com.example.shoppingapplication.dto.RateDto;
import com.example.shoppingapplication.model.Product;

public interface ProductService {
	
	List<Product> findAll();
	
	Product save(ProductDto productDto);
	
	Product findById(long id);
	
	void deleteById(long id);
	
	Product update(long id, ProductDto productDto);
	
	void rate(long id, RateDto rateDto);
	
	List<Product> search(Specification<Product> productSpecification);
}
