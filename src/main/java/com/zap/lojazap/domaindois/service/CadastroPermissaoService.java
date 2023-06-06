package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.PermissaoEntity;
import com.zap.lojazap.domaindois.exception.PermissaoNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public PermissaoEntity buscarSeTiver(Long id) {
		return permissaoRepository.findById(id)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(id));
	}

}
