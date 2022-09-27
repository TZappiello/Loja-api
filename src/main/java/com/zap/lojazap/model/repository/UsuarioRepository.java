package com.zap.lojazap.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
		
	boolean existsByEmail(String email);

}
