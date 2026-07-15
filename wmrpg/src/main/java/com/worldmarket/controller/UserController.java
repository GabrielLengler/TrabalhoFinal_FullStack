package com.worldmarket.controller;

import com.worldmarket.model.LoginRequest;
import com.worldmarket.model.LoginResponse;
import com.worldmarket.model.RegisterRequest;
import com.worldmarket.model.RegisterResponse;
import com.worldmarket.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.worldmarket.service.JwtService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	private final UserService userService;
	private final JwtService jwtService;
	
	public UserController(UserService userService, JwtService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}
		
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
		RegisterResponse response = userService.register(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		LoginResponse response = userService.login(loginRequest);
		String token = jwtService.generateToken(response.getId(), response.getUsername(), response.getRole());
		response.setToken(token);
		return ResponseEntity.ok()
			.header(HttpHeaders.SET_COOKIE, jwtService.createAuthCookie(token, loginRequest.isRemember()).toString())
			.body(response);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Void> logout() {
		return ResponseEntity.ok()
			.header(HttpHeaders.SET_COOKIE, jwtService.clearAuthCookie().toString())
			.build();
	}
}
