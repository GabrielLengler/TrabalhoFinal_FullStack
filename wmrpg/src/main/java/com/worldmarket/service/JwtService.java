package com.worldmarket.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.worldmarket.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

	private final String jwtSecret;
	private final long expirationSeconds;
	private final String cookieName;
	private final boolean cookieSecure;

	public JwtService(
		@Value("${security.jwt.secret}") String jwtSecret,
		@Value("${security.jwt.expiration-seconds}") long expirationSeconds,
		@Value("${security.jwt.cookie-name}") String cookieName,
		@Value("${security.jwt.cookie-secure}") boolean cookieSecure) {
		if (jwtSecret == null || jwtSecret.length() < 32) {
			throw new IllegalStateException("security.jwt.secret must have at least 32 characters");
		}
		this.jwtSecret = jwtSecret;
		this.expirationSeconds = expirationSeconds;
		this.cookieName = cookieName;
		this.cookieSecure = cookieSecure;
	}

	public String generateToken(User user) {
		return generateToken(user.getId(), user.getUsername(), user.getRole());
	}

	public String generateToken(long userId, String username, User.Role role) {
		Instant now = Instant.now();
		Instant expiration = now.plusSeconds(expirationSeconds);

		return Jwts.builder()
			.subject(String.valueOf(userId))
			.claim("username", username)
			.claim("role", role.name())
			.issuedAt(Date.from(now))
			.expiration(Date.from(expiration))
			.signWith(signingKey())
			.compact();
	}

	public Long extractUserId(String token) {
		String subject = Jwts.parser()
			.verifyWith(signingKey())
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getSubject();

		return Long.valueOf(subject);
	}

	public Optional<String> resolveToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return Optional.of(authHeader.substring(7));
		}

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return Optional.empty();
		}

		return Arrays.stream(cookies)
			.filter(cookie -> cookieName.equals(cookie.getName()))
			.map(Cookie::getValue)
			.findFirst();
	}

	public ResponseCookie createAuthCookie(String token) {
		return ResponseCookie.from(cookieName, token)
			.httpOnly(true)
			.secure(cookieSecure)
			.path("/")
			.sameSite("Lax")
			.maxAge(Duration.ofSeconds(expirationSeconds))
			.build();
	}

	private SecretKey signingKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
}
