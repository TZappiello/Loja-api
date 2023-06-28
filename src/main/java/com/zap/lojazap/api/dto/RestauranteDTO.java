package com.zap.lojazap.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private Boolean ativo;
	private Boolean aberto;
	
	private CozinhaDTO cozinha;
	
	private EnderecoDTO endereco;
}
