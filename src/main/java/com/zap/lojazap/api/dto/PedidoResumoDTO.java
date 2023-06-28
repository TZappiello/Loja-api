package com.zap.lojazap.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.zap.lojazap.domaindois.enums.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoDTO {

	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;     
	private OffsetDateTime dataCriacao;
	private RestauranteResumidoDTO restaurante;
//	private UsuarioDTO cliente;
	private String nomeCliente;
}
