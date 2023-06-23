package com.zap.lojazap.domaindois.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zap.lojazap.domaindois.enums.StatusPedido;
import com.zap.lojazap.domaindois.exception.NegocioException;

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
	private OffsetDateTime dataCriacao;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataConfirmacao;
	
	@UpdateTimestamp
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataCancelamento;

	@UpdateTimestamp
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataEntrega;

	@Embedded
	private Endereco endereco;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
	private StatusPedido status = StatusPedido.CRIADO;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) //cascade = CascadeType.ALL colocar isso aqui para salvar os itens.
	private List<ItemPedidoEntity> itemPedido = new ArrayList<>();
	
	public void calcularValorTotal() {
		getItemPedido().forEach(ItemPedidoEntity::calcularPrecoTotal);
		
		this.subtotal = getItemPedido().stream()
				.map(item -> item.getPrecoTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
	}
	
	private void setStatus(StatusPedido novoStatus) {
		if( getStatus().naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(
					String.format("O status do pedido %d não pode ser alterado de %s para %s ",
								getId(), 
								getStatus().getDescricao(), 
								novoStatus.getDescricao()));
		}
		
		this.status = novoStatus;
	}
}














