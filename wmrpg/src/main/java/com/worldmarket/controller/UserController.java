package com.worldmarket.controller;

import com.worldmarket.model.LoginRequest;
import com.worldmarket.model.LoginResponse;
import com.worldmarket.model.RegisterRequest;
import com.worldmarket.model.RegisterResponse;
import com.worldmarket.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
		
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
		RegisterResponse response = userService.register(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		LoginResponse response = userService.login(loginRequest);
		return ResponseEntity.ok(response);
	}
}
