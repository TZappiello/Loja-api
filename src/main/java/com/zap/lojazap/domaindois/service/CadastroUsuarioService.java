package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.UsuarioNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public UsuarioEntity buscarSeTiver(Long id) {
		UsuarioEntity entity = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradaException(id));
	
		return entity;
	}

}
