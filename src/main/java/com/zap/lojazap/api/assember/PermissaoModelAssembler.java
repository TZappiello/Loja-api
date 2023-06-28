package com.zap.lojazap.api.assember;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.PermissaoDTO;
import com.zap.lojazap.domaindois.entities.PermissaoEntity;

@Component
public class PermissaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoDTO toDTO(PermissaoEntity permissaoEntity) {
		return modelMapper.map(permissaoEntity, PermissaoDTO.class);
	}
	
	public List<PermissaoDTO> toCollectionDTO(Collection<PermissaoEntity> permissao){
		
		return permissao.stream()
			.map(cidade -> toDTO(cidade))
			.collect(Collectors.toList());
	}
}
