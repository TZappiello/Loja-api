package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.entities.PermissaoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.GrupoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removida, pois está em uso";

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissao;

	@Transactional
	public GrupoEntity cadastrar(GrupoEntity grupoEntity) {
		GrupoEntity entity = grupoRepository.save(grupoEntity);

		return entity;
	}

	public GrupoEntity buscarSeTiver(Long id) {

		GrupoEntity entity = grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));

		return entity;
	}

	@Transactional
	public void excluir(Long id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO));
		}
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		GrupoEntity grupo = buscarSeTiver(grupoId);
		PermissaoEntity permissao = cadastroPermissao.buscarSeTiver(permissaoId);
		
		grupo.desassociarPermissao(permissao);
		
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		GrupoEntity grupo = buscarSeTiver(grupoId);
		PermissaoEntity permissao = cadastroPermissao.buscarSeTiver(permissaoId);
		
		grupo.associarPermissao(permissao);
	}
}
