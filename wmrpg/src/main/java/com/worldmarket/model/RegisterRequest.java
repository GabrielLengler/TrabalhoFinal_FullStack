package com.worldmarket.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 30, message = "Username must have between 3 and 30 characters")
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Email is invalid")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 120, message = "Password must have between 8 and 120 characters")
	private String password;
	
	@NotBlank(message = "Notification preference is required")
	private boolean notification;
}
