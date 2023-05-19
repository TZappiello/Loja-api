package com.zap.lojazap.api.DTO;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private Boolean ativo;
	
	private CozinhaDTO cozinha;
	
	private EnderecoDTO endereco;
}
