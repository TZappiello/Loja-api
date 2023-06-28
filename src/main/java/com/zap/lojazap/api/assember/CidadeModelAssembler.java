package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.CidadeDTO;
import com.zap.lojazap.api.dto.CozinhaDTO;
import com.zap.lojazap.api.dto.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class CidadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeDTO toDTO(CidadeEntity cidadeEntity) {
		return modelMapper.map(cidadeEntity, CidadeDTO.class);
	}
	
	public List<CidadeDTO> toCollectionDTO(List<CidadeEntity> cidades){
		
		return cidades.stream()
			.map(cidade -> toDTO(cidade))
			.collect(Collectors.toList());
	}
}
