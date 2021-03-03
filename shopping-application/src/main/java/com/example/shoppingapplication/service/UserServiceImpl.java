package com.example.shoppingapplication.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shoppingapplication.controller.BlockDto;
import com.example.shoppingapplication.dto.UserDto;
import com.example.shoppingapplication.model.Role;
import com.example.shoppingapplication.model.User;
import com.example.shoppingapplication.repository.RoleRepository;
import com.example.shoppingapplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with username " + username + " not found");
		}
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), !user.getIsBlocked(), true, true, true, Collections.singletonList(authority));
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User save(UserDto userDto) {
		Role role = roleRepository.findByName(userDto.getRoleName()); 
		if (role == null) {
			role = new Role(userDto.getRoleName());
		}
		
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setIsBlocked(false);
		user.setRole(role);
		
		return userRepository.save(user);
	}

	@Override
	public User blockUser(Long id, BlockDto blockDto) {
		User user = userRepository.findById(id).get();	
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		user.setIsBlocked(blockDto.getIsBlocked());
		
		return userRepository.save(user);
	}
}
