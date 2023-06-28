package com.zap.lojazap.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoDTO {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	
	private CidadeResumoDTO cidade;
}
