package com.zap.lojazap.api.DTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.zap.lojazap.domaindois.enums.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private StatusPedido statusPedido;     //mudar tipagem para string depois TODO
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;
	private RestauranteResumidoDTO restaurante;
	private UsuarioDTO cliente;
	private FormaPagamentoDTO formaPAgamento;
	private EnderecoDTO endereco;
	private List<ItemPedidoDTO> itemPedido;
}
