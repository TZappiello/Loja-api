package com.zap.lojazap.api.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioIdInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;

}
