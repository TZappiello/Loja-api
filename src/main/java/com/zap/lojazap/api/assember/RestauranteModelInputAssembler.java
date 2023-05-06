package com.zap.lojazap.api.assember;

import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.RestauranteInput;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class RestauranteModelInputAssembler {


	public RestauranteEntity toDTOObject(RestauranteInput restauranteInput ) {
		RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		CozinhaEntity cozinha = new CozinhaEntity();
		cozinha.setId(restauranteInput.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		return restaurante;
	}
}
