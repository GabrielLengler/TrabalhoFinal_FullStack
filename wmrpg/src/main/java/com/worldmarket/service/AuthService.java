package com.worldmarket.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worldmarket.exception.InvalidUserException;
import com.worldmarket.model.LoginRequest;
import com.worldmarket.model.LoginResponse;
import com.worldmarket.model.RegisterRequest;
import com.worldmarket.model.RegisterResponse;
import com.worldmarket.model.UpdateUserRequest;
import com.worldmarket.model.User;
import com.worldmarket.model.UserMeResponse;
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

	@Transactional(readOnly = true)
	public UserMeResponse getCurrentUser(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new InvalidUserException("Authenticated user was not found"));
		return UserMeResponse.fromUser(user);
	}

	@Transactional
	public UserMeResponse updateCurrentUser(Long userId, UpdateUserRequest request) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new InvalidUserException("Authenticated user was not found"));

		if (request.getUsername() != null) {
			String username = request.getUsername().trim();
			if (username.isEmpty()) {
				throw new InvalidUserException("Username cannot be empty");
			}
			if (!username.equals(user.getUsername()) && userRepository.existsByUsername(username)) {
				throw new InvalidUserException("Username already exists");
			}
			user.setUsername(username);
		}

		if (request.getEmail() != null) {
			String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
			if (email.isEmpty()) {
				throw new InvalidUserException("Email cannot be empty");
			}
			if (!email.equals(user.getEmail()) && userRepository.existsByEmail(email)) {
				throw new InvalidUserException("Email already exists");
			}
			user.setEmail(email);
		}

		if (request.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		if (request.getNotification() != null) {
			user.setNotification(request.getNotification());
		}

		if (request.getProfilePicture() != null) {
			String profilePicture = request.getProfilePicture().trim();
			user.setProfilePicture(profilePicture.isEmpty() ? null : profilePicture);
		}

		User savedUser = userRepository.save(user);
		return UserMeResponse.fromUser(savedUser);
	}

	@Transactional
	public UserMeResponse updateProfilePicture(Long userId, MultipartFile file) {
		if (file.isEmpty()) {
			throw new InvalidUserException("Profile picture is required");
		}

		String contentType = file.getContentType();
		if (!Set.of("image/jpeg", "image/png", "image/webp").contains(contentType)) {
			throw new InvalidUserException("Profile picture must be a PNG, JPEG, or WebP image");
		}
		if (file.getSize() > 5 * 1024 * 1024) {
			throw new InvalidUserException("Profile picture must be 5 MB or smaller");
		}

		String extension = switch (contentType) {
			case "image/png" -> ".png";
			case "image/webp" -> ".webp";
			default -> ".jpg";
		};
		String filename = UUID.randomUUID() + extension;
		Path uploadDirectory = Path.of("uploads", "profile-pictures").toAbsolutePath();

		try {
			Files.createDirectories(uploadDirectory);
			file.transferTo(uploadDirectory.resolve(filename));
		} catch (IOException ex) {
			throw new IllegalStateException("Could not store profile picture", ex);
		}

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new InvalidUserException("Authenticated user was not found"));
		String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/uploads/profile-pictures/")
			.path(filename)
			.toUriString();
		user.setProfilePicture(imageUrl);

		return UserMeResponse.fromUser(userRepository.save(user));
	}
}
