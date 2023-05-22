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
	
	public GrupoEntity buscarSeTiver(Long id) {
		return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}
}


//public FormaPagamentoEntity buscarSeTiver(Long id) {
//	return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaDePagamentoNaoEncontradoException(id));
//}
