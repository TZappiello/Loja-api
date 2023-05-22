package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.GrupoIdInput;
import com.zap.lojazap.domaindois.entities.GrupoEntity;

@Component
public class GrupoModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoEntity toDTOObject(GrupoIdInput grupoIdInput) {
	
		return modelMapper.map(grupoIdInput, GrupoEntity.class);
	}
	
	public void copyToDtoObject(GrupoIdInput grupoInput, GrupoEntity grupoEntity) {
		grupoInput.setId(grupoEntity.getId());

		modelMapper.map(grupoInput, grupoEntity);
	}
}
