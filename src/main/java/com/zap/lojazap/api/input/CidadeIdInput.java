package com.zap.lojazap.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInput {

	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private EstadoIdInput estado;
}
