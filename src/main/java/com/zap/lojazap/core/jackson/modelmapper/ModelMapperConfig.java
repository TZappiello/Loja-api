package com.zap.lojazap.core.jackson.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zap.lojazap.api.dto.EnderecoDTO;
import com.zap.lojazap.api.input.ItemPedidoInput;
import com.zap.lojazap.domaindois.entities.Endereco;
import com.zap.lojazap.domaindois.entities.ItemPedidoEntity;

@Configuration
public class ModelMapperConfig {

	@Bean
	public  ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(RestauranteEntity.class,RestauranteDTO.class)
//			.addMapping(RestauranteEntity::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedidoEntity.class)
			.addMappings(mapper -> mapper.skip(ItemPedidoEntity::setId)); //aqui estou ignorando setar o id no itemPedidoEntity 
		
	 var enderecoToEnderecoDtoTypeMap =	modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
	 
	 enderecoToEnderecoDtoTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), 
			 	(enderecoDtoDest, enderecoSrc)-> enderecoDtoDest.getCidade().setEstado(enderecoSrc));
	 
		
		return modelMapper;
	}
}
