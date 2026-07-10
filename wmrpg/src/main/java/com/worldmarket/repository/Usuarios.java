package com.worldmarket.repository;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 128)
	private int id;

	@Column(name = "username", nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(name = "email", nullable = false, length = 120, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false, length = 120)
	private String password;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "notification", nullable = false)
	private boolean notification = false;
	
	@Column(name = "foto_perfil", nullable = false, length = 120)
	private String fotoPerfil = null;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	
	enum Role {
		ADMINISTRATOR, USER
	}
}
