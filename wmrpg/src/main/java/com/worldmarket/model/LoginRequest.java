package com.worldmarket.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	@NotBlank(message = "Username or email is required")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 120, message = "Password must have between 8 and 120 characters")
	private String password;

	private boolean remember;
}
