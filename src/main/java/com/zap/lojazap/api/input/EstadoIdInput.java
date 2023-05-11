package com.zap.lojazap.api.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {
	
	@NotBlank
	private String nome;
}
