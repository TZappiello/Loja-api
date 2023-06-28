package com.zap.lojazap.api.assember;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zap.lojazap.api.dto.CozinhaDTO;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;

@Component
public class CozinhaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaDTO toDTO(CozinhaEntity cozinhaEntity) {
		return modelMapper.map(cozinhaEntity, CozinhaDTO.class);

	}

	public List<CozinhaDTO> toCollectionDTO(List<CozinhaEntity> cozinhas) {
		return cozinhas.stream()
				.map(cozinha -> toDTO(cozinha))
				.collect(Collectors.toList());
	}

}
