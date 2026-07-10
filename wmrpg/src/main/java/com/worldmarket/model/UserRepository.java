package com.worldmarket.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.worldmarket.repository.Usuarios;

public interface UserRepository extends JpaRepository<Usuarios, Long> {
	Optional<Usuarios> findByUsername(String username);
	Optional<Usuarios> findByEmail(String email);
	Optional<Usuarios> findById(Long id);
}
