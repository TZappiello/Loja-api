package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.zap.lojazap.api.DTO.CozinhaDTO;
import com.zap.lojazap.api.DTO.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class RestauranteModelAssembler {

	public RestauranteDTO toDTO(RestauranteEntity restauranteEntity) {
		CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setId(restauranteEntity.getCozinha().getId());
		cozinhaDTO.setNome(restauranteEntity.getCozinha().getNome());
		
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setId(restauranteEntity.getId());
		restauranteDTO.setNome(restauranteEntity.getNome());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;
	}
	
	public List<RestauranteDTO> toCollectionDTO(List<RestauranteEntity> restaurantes){
		
		return restaurantes.stream()
			.map(restaurante -> toDTO(restaurante))
			.collect(Collectors.toList());
	}
}
