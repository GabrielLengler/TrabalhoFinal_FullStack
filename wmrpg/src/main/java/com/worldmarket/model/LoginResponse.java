package com.worldmarket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	private long id;
	private String username;
	private String email;
	private User.Role role;
	private String token;

	public static LoginResponse fromUser(User user) {
		LoginResponse response = new LoginResponse();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		return response;
	}
}
