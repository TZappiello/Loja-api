package com.zap.lojazap.api.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

	private Long id;
	private String nome;
	
	private EstadoDTO estado;
		
}
