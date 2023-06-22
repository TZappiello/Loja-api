package com.zap.lojazap.domaindois.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_pedido")
@Entity
public class ItemPedidoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantidade", nullable = false)
	private int quantidade;
	
	@Column(name = "preco_unitario", nullable = false)
	private BigDecimal precoUnitario;
	
	@Column(name = "preco_total", nullable = false)
	private BigDecimal precoTotal;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private PedidoEntity pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private ProdutoEntity produto;
	
	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getPrecoUnitario();
		Integer quantidade = this.getQuantidade();
		
		if(precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}
		
		this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
	}

}
