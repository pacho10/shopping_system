package com.example.shoppingapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapplication.dto.ProductDto;
import com.example.shoppingapplication.dto.RateDto;
import com.example.shoppingapplication.model.Product;
import com.example.shoppingapplication.search.ProductSpecification;
import com.example.shoppingapplication.search.SearchCriteria;
import com.example.shoppingapplication.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAll() {
		return productService.findAll();
	}
	
	@GetMapping("products/{id}")
	public Product getById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody ProductDto productDto) {
		return productService.save(productDto);
	}
	
	@PutMapping("products/{id}")
	public Product updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
		return productService.update(id, productDto);
	}
	
	@DeleteMapping("products/{id}")
	public void deleteProductById(@PathVariable Long id) {
		productService.deleteById(id);
	}
	
	@PostMapping("/rate/{id}")
	public void rate(@PathVariable Long id, @Valid @RequestBody RateDto rateDto) {
		productService.rate(id,  rateDto);
	}
	
	@GetMapping("search")
	public List<Product> searchProduct(@RequestParam(value = "search") String search) {
		String[] searchArr = search.split(":|<|>");
		String key = searchArr[0];
		String value = searchArr[1];
		String operator = search.substring(key.length(), key.length() + 1);
        SearchCriteria searchCriteria = new SearchCriteria(key, operator, value);
        Specification<Product> specification = new ProductSpecification(searchCriteria);
        
		return productService.search(specification);
	}
}
