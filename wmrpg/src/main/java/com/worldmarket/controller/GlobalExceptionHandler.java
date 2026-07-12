package com.worldmarket.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.worldmarket.exception.InvalidUserException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUser(InvalidUserException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.findFirst()
			.map(FieldError::getDefaultMessage)
			.orElse("Validation error");

		String fields = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(FieldError::getField)
			.distinct()
			.collect(Collectors.joining(", "));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
			"error", message,
			"fields", fields));
	}
}
