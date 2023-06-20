package com.zap.lojazap.api.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

	@NotNull
	private Long produtoId;
	
	@NotNull
	@PositiveOrZero
	private int quantidade;
	
	private String observacao;
}
