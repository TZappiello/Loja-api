package com.zap.lojazap.api.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO extends RepresentationModel<EstadoDTO> {

	private Long id;
	private String nome;
		
}
