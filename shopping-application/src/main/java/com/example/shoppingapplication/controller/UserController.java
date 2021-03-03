package com.example.shoppingapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingapplication.dto.UserDto;
import com.example.shoppingapplication.model.User;
import com.example.shoppingapplication.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registration/users")
	public User registerUser(@Valid @RequestBody UserDto userDto) {
		if (userDto.isPasswordMatch() == false) {
			// throw exception
		}
		return userService.save(userDto);
	}
	
	@PutMapping("/block/users/{id}")
	public User blockUser(@PathVariable Long id, @Valid @RequestBody BlockDto blockDto) {
		return userService.blockUser(id, blockDto);
	}
}
