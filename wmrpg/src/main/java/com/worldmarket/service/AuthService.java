package com.worldmarket.service;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.worldmarket.exception.InvalidUserException;
import com.worldmarket.model.LoginRequest;
import com.worldmarket.model.LoginResponse;
import com.worldmarket.model.RegisterRequest;
import com.worldmarket.model.RegisterResponse;
import com.worldmarket.model.User;
import com.worldmarket.repository.UserRepository;

@Service
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Transactional
	public RegisterResponse register(RegisterRequest request) {
		String username = request.getUsername().trim();
		String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
		String rawPassword = request.getPassword();
		boolean notification = request.getNotification();

		if (userRepository.existsByUsername(username)) {
			throw new InvalidUserException("Username already exists");
		}
		if (userRepository.existsByEmail(email)) {
			throw new InvalidUserException("Email already exists");
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(rawPassword));
		user.setRole(User.Role.USER);
		user.setNotification(notification);

		User savedUser = userRepository.save(user);
		return RegisterResponse.fromUser(savedUser);
	}

	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest request) {
		String identity = request.getUsername().trim();
		String password = request.getPassword();

		User user = identity.contains("@")
			? userRepository.findByEmail(identity.toLowerCase(Locale.ROOT)).orElseThrow(
				() -> new InvalidUserException("Invalid username/email or password"))
			: userRepository.findByUsername(identity).orElseThrow(
				() -> new InvalidUserException("Invalid username/email or password"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidUserException("Invalid username/email or password");
		}

		return LoginResponse.fromUser(user);
	}
}
