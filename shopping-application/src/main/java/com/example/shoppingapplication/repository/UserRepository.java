package com.example.shoppingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingapplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
