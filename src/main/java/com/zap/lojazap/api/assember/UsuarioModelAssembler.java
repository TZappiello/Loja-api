package com.zap.lojazap.api.assember;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.UsuarioDTO;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;

@Component
public class UsuarioModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioDTO toDTO(UsuarioEntity usuarioEntity) {
		return modelMapper.map(usuarioEntity, UsuarioDTO.class);
	}
	
	public List<UsuarioDTO> toCollectionDTO(Collection<UsuarioEntity> usuarios){
		
		return usuarios.stream()
			.map(usuario -> toDTO(usuario))
			.collect(Collectors.toList());
	}
}
