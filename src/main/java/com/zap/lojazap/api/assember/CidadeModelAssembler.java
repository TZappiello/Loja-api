package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.DTO.CidadeDTO;
import com.zap.lojazap.api.DTO.CozinhaDTO;
import com.zap.lojazap.api.DTO.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class CidadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeDTO toDTO(CidadeEntity cidadeEntity) {
		return modelMapper.map(cidadeEntity, CidadeDTO.class);
		
		/*CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setId(restauranteEntity.getCozinha().getId());
		cozinhaDTO.setNome(restauranteEntity.getCozinha().getNome());
		
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setId(restauranteEntity.getId());
		restauranteDTO.setNome(restauranteEntity.getNome());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;*/
	}
	
	public List<CidadeDTO> toCollectionDTO(List<CidadeEntity> cidades){
		
		return cidades.stream()
			.map(cidade -> toDTO(cidade))
			.collect(Collectors.toList());
	}
}