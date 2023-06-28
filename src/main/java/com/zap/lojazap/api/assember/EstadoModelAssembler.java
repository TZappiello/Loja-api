package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.CidadeDTO;
import com.zap.lojazap.api.dto.EstadoDTO;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;

@Component
public class EstadoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoDTO toDTO(EstadoEntity estadoEntity) {
		return modelMapper.map(estadoEntity, EstadoDTO.class);
		
		/*CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setId(restauranteEntity.getCozinha().getId());
		cozinhaDTO.setNome(restauranteEntity.getCozinha().getNome());
		
		RestauranteDTO restauranteDTO = new RestauranteDTO();
		restauranteDTO.setId(restauranteEntity.getId());
		restauranteDTO.setNome(restauranteEntity.getNome());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;*/
	}
	
	public List<EstadoDTO> toCollectionDTO(List<EstadoEntity> estados){
		
		return estados.stream()
			.map(estado -> toDTO(estado))
			.collect(Collectors.toList());
	}
}
