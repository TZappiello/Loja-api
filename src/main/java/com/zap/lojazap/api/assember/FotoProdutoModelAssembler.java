package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.FotoProdutoDTO;
import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;

@Component
public class FotoProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoDTO toDTO(FotoProdutoEntity foto) {
		return modelMapper.map(foto, FotoProdutoDTO.class);
		
	}
	
}
