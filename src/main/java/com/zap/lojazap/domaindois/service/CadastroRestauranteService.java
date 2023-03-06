package com.zap.lojazap.domaindois.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADA 
	= "Não existe um cadastro de Restaurante com código %d";
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public RestauranteEntity cadastrar(RestauranteEntity restaurante) {
		
		Optional<RestauranteEntity> taxa = restauranteRepository.findTaxaByTaxaFrete(restaurante.getTaxaFrete());
		Optional<RestauranteEntity> contem = restauranteRepository.findNomeCompletoByNome(restaurante.getNome());
		
		if(taxa.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Taxa Frete com o mesmo valor ja esta cadastrada!!!! "));
		}
		
		if(contem.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Esse Restaurante ja esta cadastrado tente novamente! "));
		}
		
		Long cozinhaId = restaurante.getCozinha().getId();
		CozinhaEntity cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format("Não existe cozinha cadastra com código %d ", cozinhaId)));
		

		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
		
	}

	public RestauranteEntity buscarSeTiver(Long id) {
		return restauranteRepository.findById(id)
				.orElseThrow(()->
				new EntidadeNaoEncontradaException( 
				String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, id)));
	}
	
}
