package com.example.shoppingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingapplication.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
