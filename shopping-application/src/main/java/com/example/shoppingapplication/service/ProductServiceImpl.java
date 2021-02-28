package com.example.shoppingapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.shoppingapplication.dto.ProductDto;
import com.example.shoppingapplication.dto.RateDto;
import com.example.shoppingapplication.model.Category;
import com.example.shoppingapplication.model.Product;
import com.example.shoppingapplication.repository.CategoryRepository;
import com.example.shoppingapplication.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryReposytory;
	
	@Override
	public Product save(ProductDto productDto) {
		Category category = categoryReposytory.findByName(productDto.getCategory());
		if (category == null) {
			category = new Category(productDto.getCategory());
		}
		Product product = new Product();
		product.setName(productDto.getName());
		product.setCategory(category);
		product.setPrice(productDto.getPrice());
		product.setRateCount(0);
		product.setRate(0.0);
		
		return productRepository.save(product);
	}

	@Override
	public Product findById(long id) {
		Optional<Product> optional = productRepository.findById(id);
		return optional.get();
	}

	@Override
	public void deleteById(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product update(long id, ProductDto productDto) {
		Product toUpdate = productRepository.findById(id).get();
		toUpdate.setName(productDto.getName());
		toUpdate.setPrice(productDto.getPrice());
		return productRepository.save(toUpdate);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public void rate(long id, RateDto rateDto) {
		Product product = productRepository.findById(id).get();
		if (product != null) {
			int rateCountOld = product.getRateCount();
			double rateOld = product.getRate();
			
			if (rateCountOld == 0) {
				product.setRateCount(1);
				product.setRate((double)rateDto.getRate());
			} else {
				int rateCounntNew = ++rateCountOld;
				double ratenew = ((rateOld * rateCountOld) + rateDto.getRate()) / rateCounntNew;
				product.setRateCount(rateCounntNew);
				product.setRate(ratenew);
			}
			
			productRepository.save(product);
		}
	}

	@Override
	public List<Product> search(Specification<Product> spec) {
		return productRepository.findAll(spec);
	}
}
