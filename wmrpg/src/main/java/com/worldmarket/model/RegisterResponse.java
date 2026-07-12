package com.worldmarket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
	private long id;
	private String username;
	private String email;

	public static RegisterResponse fromUser(User user) {
		RegisterResponse response = new RegisterResponse();
		response.setId(user.getId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		return response;
	}
}
