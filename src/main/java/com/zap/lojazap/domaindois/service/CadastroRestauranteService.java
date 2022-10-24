package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.model.CozinhaEntity;
import com.zap.lojazap.domaindois.model.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public RestauranteEntity cadastrar(RestauranteEntity restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		CozinhaEntity cozinha = cozinhaRepository.porId(cozinhaId);
		
		Long restauranteId = restaurante.getId();
		RestauranteEntity restauranteContem = restauranteRepository.porId(restauranteId);
		
		if(restauranteContem == null) {
			throw new EntidadeEmUsoException(
					String.format("O código do restaurante não pode ser %d", restaurante));
		}
		 
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cozinha cadastra com código %d ", cozinhaId));
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.adicionar(restaurante);
		
	}
	
	
}
