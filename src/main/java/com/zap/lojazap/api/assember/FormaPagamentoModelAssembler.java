package com.zap.lojazap.api.assember;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.CidadeDTO;
import com.zap.lojazap.api.dto.CozinhaDTO;
import com.zap.lojazap.api.dto.FormaPagamentoDTO;
import com.zap.lojazap.api.dto.RestauranteDTO;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class FormaPagamentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoDTO toDTO(FormaPagamentoEntity formaPagamentoEntity) {
		return modelMapper.map(formaPagamentoEntity, FormaPagamentoDTO.class);

	}
	
	public List<FormaPagamentoDTO> toCollectionDTO(Collection<FormaPagamentoEntity> list){
		
		return list.stream()
			.map(lista -> toDTO(lista))
			.collect(Collectors.toList());
	}
}
