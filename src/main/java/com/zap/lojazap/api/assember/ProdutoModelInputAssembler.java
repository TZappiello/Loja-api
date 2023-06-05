package com.zap.lojazap.api.assember;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.input.ProdutoInput;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;

@Component
public class ProdutoModelInputAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoEntity toDTOObject(ProdutoInput produtoIdInput) {
		return modelMapper.map(produtoIdInput, ProdutoEntity.class);
	}
	
	public void copyToDtoObject(ProdutoInput produtoInput, ProdutoEntity produtoEntity) {
		modelMapper.map(produtoInput, produtoEntity);
	}
}
