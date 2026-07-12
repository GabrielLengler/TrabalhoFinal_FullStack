package com.worldmarket.service;

import org.springframework.stereotype.Service;

import com.worldmarket.model.LoginRequest;
import com.worldmarket.model.LoginResponse;
import com.worldmarket.model.RegisterRequest;
import com.worldmarket.model.RegisterResponse;

@Service
public class UserService {
	
	private final AuthService authService;
	
	public UserService(AuthService authService) {
		this.authService = authService;
	}
	
	public LoginResponse login(LoginRequest request) {
		return authService.login(request);
	}
	
	public RegisterResponse register(RegisterRequest request) {
		return authService.register(request);
	}
}
