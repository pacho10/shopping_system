package com.example.shoppingapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapplication.dto.UserDto;
import com.example.shoppingapplication.model.User;
import com.example.shoppingapplication.service.UserService;

@RestController
@RequestMapping("/registration")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String testUser() {
		return "It Works!";
	}
	
	@PostMapping("/users")
	public User registerUser(@Valid @RequestBody UserDto userDto) {
		if (userDto.isPasswordMatch() == false) {
			// throw exception
		}
		return userService.save(userDto);
	}
	
}
