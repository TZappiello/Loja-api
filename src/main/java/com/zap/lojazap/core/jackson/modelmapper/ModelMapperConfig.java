package com.zap.lojazap.core.jackson.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zap.lojazap.api.DTO.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Configuration
public class ModelMapperConfig {

	@Bean
	public  ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(RestauranteEntity.class,RestauranteDTO.class)
			.addMapping(RestauranteEntity::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		
		return modelMapper;
	}
}
