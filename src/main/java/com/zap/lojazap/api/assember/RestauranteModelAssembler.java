package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.CozinhaDTO;
import com.zap.lojazap.api.dto.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class RestauranteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteDTO toDTO(RestauranteEntity restauranteEntity) {
		return modelMapper.map(restauranteEntity, RestauranteDTO.class);
		
		/*CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setId(restauranteEntity.getCozinha().getId());
		cozinhaDTO.setNome(restauranteEntity.getCozinha().getNome());
		
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setId(restauranteEntity.getId());
		restauranteDTO.setNome(restauranteEntity.getNome());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;*/
	}
	
	public List<RestauranteDTO> toCollectionDTO(List<RestauranteEntity> restaurantes){
		
		return restaurantes.stream()
			.map(restaurante -> toDTO(restaurante))
			.collect(Collectors.toList());
	}
}
