package com.zap.lojazap.domaindois.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.zap.lojazap.core.validation.TaxaFrete;
import com.zap.lojazap.core.validation.ValorZeroDescricao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ValorZeroDescricao(valorField ="taxaFrete",
		descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurante")
@Entity
public class RestauranteEntity {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotNull 
//	@NotEmpty
//	@NotBlank 	(groups = Groups.CadastroRestaurante.class)
	@Column(nullable = false)
	private String nome;

//	@PositiveOrZero  	//(message = "{TaxaFrete.invalida}") traz uma mensagem especifica
//	@Multiplo(numero = 5) 		minha própria anotação com regra de negócio
//	@DecimalMin("0")
	@NotNull 
	@TaxaFrete    	//minha própria anotação
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;
	
	
//	@Valid
//	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//	@NotNull
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id")
	private CozinhaEntity cozinha;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo = Boolean.TRUE;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name ="restaurante_id"),
			inverseJoinColumns = @JoinColumn(name ="forma_pagamento_id"))
	private List<FormaPagamentoEntity> formasPagamento = new ArrayList<>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<ProdutoEntity> produtos = new ArrayList<>();

	public void ativar() {
		setAtivo(true);
//		this.ativo = true;
	}
	
	public void inativar() {
		setAtivo(false);
//		this.ativo = false;
	}
}
