package com.worldmarket.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMeResponse {
	private long id;
	private String username;
	private String email;
	private User.Role role;
	private boolean notification;
	private String profilePicture;
	private LocalDateTime createdAt;

	public static UserMeResponse fromUser(User user) {
		UserMeResponse response = new UserMeResponse();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		response.setNotification(user.isNotification());
		response.setProfilePicture(user.getProfilePicture());
		response.setCreatedAt(user.getCreatedAt());
		return response;
	}
}
