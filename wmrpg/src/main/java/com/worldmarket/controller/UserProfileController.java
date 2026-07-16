package com.worldmarket.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PutMapping(value = "/me/profile-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserMeResponse> updateProfilePicture(
		@AuthenticationPrincipal Long userId,
		@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok(userService.updateProfilePicture(userId, file));
	}
}
