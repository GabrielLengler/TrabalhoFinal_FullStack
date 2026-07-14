package com.worldmarket.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

	@Size(min = 3, max = 30, message = "Username must have between 3 and 30 characters")
	private String username;

	@Email(message = "Email is invalid")
	private String email;

	@Size(min = 8, max = 120, message = "Password must have between 8 and 120 characters")
	private String password;

	private Boolean notification;

	@Size(max = 120, message = "Profile picture must have at most 120 characters")
	private String profilePicture;
}
