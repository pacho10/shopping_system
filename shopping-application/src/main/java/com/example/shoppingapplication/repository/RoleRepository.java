package com.example.shoppingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingapplication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
