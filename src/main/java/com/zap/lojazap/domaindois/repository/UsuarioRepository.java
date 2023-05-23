package com.zap.lojazap.domaindois.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.domaindois.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByEmail(String email);
}
