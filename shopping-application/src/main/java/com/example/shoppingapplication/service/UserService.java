package com.example.shoppingapplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.shoppingapplication.controller.BlockDto;
import com.example.shoppingapplication.dto.UserDto;
import com.example.shoppingapplication.model.User;

public interface UserService extends UserDetailsService {
	
	User findByUsername(String username);
	
	User save(UserDto userDto);
	
	User blockUser(Long id, BlockDto blockDto);
}
