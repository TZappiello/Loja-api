package com.zap.lojazap.domaindois.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.exception.RestauranteNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO
	= "Restaurante de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Transactional
	public RestauranteEntity cadastrar(RestauranteEntity restaurante) {
		
		Optional<RestauranteEntity> taxa = restauranteRepository.findTaxaByTaxaFrete(restaurante.getTaxaFrete());
		Optional<RestauranteEntity> contem = restauranteRepository.findNomeCompletoByNome(restaurante.getNome());
		
		if(taxa.isPresent()) {
			throw new RestauranteNaoEncontradoException(restaurante.getId());
		}
		
		if(contem.isPresent()) {
			throw new  RestauranteNaoEncontradoException(restaurante.getId());
		}

		CozinhaEntity cozinha = cadastroCozinhaService.buscarSeTiver(restaurante.getCozinha().getId());
		
//		Long cozinhaId = restaurante.getCozinha().getId();
//		CozinhaEntity cozinha = cozinhaRepository.findById(cozinhaId)
//				.orElseThrow(()-> new EntidadeNaoEncontradaException(
//						String.format("Não existe cozinha cadastra com código %d ", cozinhaId)));
		

		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
		
	}

	public RestauranteEntity buscarSeTiver(Long id) {
		return restauranteRepository.findById(id)
				.orElseThrow(()->
				new  RestauranteNaoEncontradoException(id));
	}
	
}
