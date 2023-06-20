package com.zap.lojazap.api.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {
	
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoInput formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInput> itemPedido;
	
}
