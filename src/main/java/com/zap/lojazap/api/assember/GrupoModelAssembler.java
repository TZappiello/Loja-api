package com.zap.lojazap.api.assember;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.GrupoDTO;
import com.zap.lojazap.domaindois.entities.GrupoEntity;

@Component
public class GrupoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoDTO toDTO(GrupoEntity grupoEntity) {
		return modelMapper.map(grupoEntity, GrupoDTO.class);
	}
	
	public List<GrupoDTO> toCollectionDTO(Collection<GrupoEntity> grupos){
		
		return grupos.stream()
			.map(grupo -> toDTO(grupo))
			.collect(Collectors.toList());
	}
}
