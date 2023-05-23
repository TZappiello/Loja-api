package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.api.input.UsuarioInputAtualizarSenha;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exception.UsuarioNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioEntity cadastrar(UsuarioEntity usuarioEntity) {
		return usuarioRepository.save(usuarioEntity);
	}
	
	public UsuarioEntity buscarSeTiver(Long id) {
		UsuarioEntity entity = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradaException(id));
	
		return entity;
	}

	@Transactional
	public UsuarioEntity AtualizarSenha(Long id, UsuarioInputAtualizarSenha atualizarSenha) {
		UsuarioEntity usuarioEntity = buscarSeTiver(id);
		
		if(!usuarioEntity.getSenha().equals(atualizarSenha.getSenhaAtual())) {
			throw new NegocioException(String.format("Senha atual informada não coincide com a senha do usuário."));
		}
		usuarioEntity.setSenha(atualizarSenha.getNovaSenha());
		
		return usuarioRepository.save(usuarioEntity);
	}
}
