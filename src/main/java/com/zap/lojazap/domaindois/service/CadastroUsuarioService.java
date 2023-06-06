package com.zap.lojazap.domaindois.service;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.api.input.UsuarioInputAtualizarSenha;
import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exception.UsuarioNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Transactional
	public UsuarioEntity cadastrar(UsuarioEntity usuarioEntity) {
		
		manager.detach(usuarioEntity); //desconecta * tira essa instancia do contexto de persistência não faz o commit e depois o rallback
		
		Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(usuarioEntity.getEmail());
		
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuarioEntity) ) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail: %s ", usuarioEntity.getEmail()));
		} 
		
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

	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		UsuarioEntity usuario = buscarSeTiver(usuarioId);
		GrupoEntity grupo = cadastroGrupo.buscarSeTiver(grupoId);
		
		usuario.desassociar(grupo);
		
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		UsuarioEntity usuario = buscarSeTiver(usuarioId);
		GrupoEntity grupo = cadastroGrupo.buscarSeTiver(grupoId);
		
		usuario.associar(grupo);
	}
}
