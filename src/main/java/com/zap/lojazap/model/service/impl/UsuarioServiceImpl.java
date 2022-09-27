package com.zap.lojazap.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.model.entity.Usuario;
import com.zap.lojazap.model.exception.RegraDeNegocioException;
import com.zap.lojazap.model.repository.UsuarioRepository;
import com.zap.lojazap.model.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;	

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticar(String nome, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {

		boolean existe = usuarioRepository.existsByEmail(email);
		if(existe) {
			throw new RegraDeNegocioException("Esse email ja está cadastrado!");
		}
		
	}

}
