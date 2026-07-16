package com.worldmarket.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.worldmarket.model.LoginRequest;
import com.worldmarket.model.LoginResponse;
import com.worldmarket.model.RegisterRequest;
import com.worldmarket.model.RegisterResponse;
import com.worldmarket.model.UpdateUserRequest;
import com.worldmarket.model.UserMeResponse;

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

	public UserMeResponse getCurrentUser(Long userId) {
		return authService.getCurrentUser(userId);
	}

	public UserMeResponse updateCurrentUser(Long userId, UpdateUserRequest request) {
		return authService.updateCurrentUser(userId, request);
	}

	public UserMeResponse updateProfilePicture(Long userId, MultipartFile file) {
		return authService.updateProfilePicture(userId, file);
	}
}
