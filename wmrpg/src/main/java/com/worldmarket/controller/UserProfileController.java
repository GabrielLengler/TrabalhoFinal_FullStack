package com.worldmarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldmarket.model.UpdateUserRequest;
import com.worldmarket.model.UserMeResponse;
import com.worldmarket.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserProfileController {

	private final UserService userService;

	public UserProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/me")
	public ResponseEntity<UserMeResponse> me(@AuthenticationPrincipal Long userId) {
		return ResponseEntity.ok(userService.getCurrentUser(userId));
	}

	@PutMapping("/me")
	public ResponseEntity<UserMeResponse> updateMe(
		@AuthenticationPrincipal Long userId,
		@Valid @RequestBody UpdateUserRequest request) {
		return ResponseEntity.ok(userService.updateCurrentUser(userId, request));
	}
}
