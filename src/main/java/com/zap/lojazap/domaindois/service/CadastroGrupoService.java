package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.exception.GrupoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	public GrupoEntity cadastrar(GrupoEntity grupoEntity) {
		GrupoEntity entity = grupoRepository.save(grupoEntity);
		
		return entity;
	}
	
	
	public GrupoEntity buscarSeTiver(Long id) {
		
		GrupoEntity entity = grupoRepository.findById(id)
				.orElseThrow(() -> new GrupoNaoEncontradoException(id));
	
		return entity;
	}
}


