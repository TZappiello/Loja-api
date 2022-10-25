package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.model.EstadoEntity;
import com.zap.lojazap.domaindois.repository.EstadoRepository;

@Service
public class CadastroEstadosService {

	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public EstadoEntity adicionar(EstadoEntity estados) {
		return estadoRepository.adicionar(estados);
	}
	
//	public EstadoEntity cadastrar(EstadoEntity estado) {
//		
//		Long cozinhaId = restaurante.getCozinha().getId();
//		CozinhaEntity cozinha = cozinhaRepository.porId(cozinhaId);
//		
//		if(cozinha == null) {
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não existe cozinha cadastra com código %d ", cozinhaId));
//		}
//		
//		restaurante.setCozinha(cozinha);
//		
//		return restauranteRepository.adicionar(restaurante);
//		
//	}
	
	
}
