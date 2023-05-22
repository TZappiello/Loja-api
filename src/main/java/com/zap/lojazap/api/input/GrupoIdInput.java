package com.zap.lojazap.api.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoIdInput {

	private Long id;

	@NotBlank
	private String nome;
}
