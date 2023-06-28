package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.ProdutoDTO;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;

@Component
public class ProdutoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoDTO toDTO(ProdutoEntity produtoEntity) {
		return modelMapper.map(produtoEntity, ProdutoDTO.class);
	}
	
	public List<ProdutoDTO> toCollectionDTO(List<ProdutoEntity> produtos ) {
		
		return produtos.stream()
				.map(produto -> toDTO(produto))
				.collect(Collectors.toList());
	}

}

