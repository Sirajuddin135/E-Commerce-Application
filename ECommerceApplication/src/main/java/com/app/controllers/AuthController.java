package com.app.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.UserNotFoundException;
import com.app.payloads.LoginCredentials;
import com.app.payloads.UserDTO;
import com.app.security.JWTUtil;
import com.app.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public Map<String, Object> registerHandler(@Valid @RequestBody UserDTO user) throws UserNotFoundException {
		String encodedPass = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPass);
		
		UserDTO userDTO = userService.registerUser(user);
		
		String token = jwtUtil.generateToken(userDTO.getEmail());
		
		return Collections.singletonMap("jwt-token", token);
	}
	
	@PostMapping("/login")
	public Map<String, Object> loginHandler(@RequestBody LoginCredentials credentials) {
		try {
			UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
			
			authenticationManager.authenticate(authCredentials);
			
			String token = jwtUtil.generateToken(credentials.getEmail());
			
			return Collections.singletonMap("jwt-token", token);
			
		} catch (AuthenticationException authException) {
			throw new RuntimeException("Invalid login credentials !!");
		}
	}
}