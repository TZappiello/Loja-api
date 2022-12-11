package com.zap.lojazap.domaindois.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produtos")
@Entity
public class ProdutoEntity {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal preco;
	
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	private RestauranteEntity restaurante;
	
	@OneToMany(mappedBy = "produto")
	private List<ItemPedidoEntity> itemPedido = new ArrayList<>();
	
}
