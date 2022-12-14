package com.zap.lojazap.domaindois.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zap.lojazap.domaindois.enums.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
@Entity
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sub_total", nullable = false)
	private BigDecimal subtotal;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCriacao;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	
	@UpdateTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;

	@UpdateTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataEntrega;

	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "forma_pagamento", nullable = false)
	private FormaPagamentoEntity formaPAgamento;
	
	@ManyToOne
	@JoinColumn(name = "restaurante", nullable = false)
	private RestauranteEntity restaurante;
	
	@ManyToOne
	@JoinColumn(name = "cliente_usuario", nullable = false)
	private UsuarioEntity cliente;
	
	@Column(name = "status_pedido", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.CRIADO;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedidoEntity> itemPedido = new ArrayList<>();
	
}














