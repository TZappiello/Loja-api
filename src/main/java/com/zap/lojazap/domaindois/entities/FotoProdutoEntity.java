package com.zap.lojazap.domaindois.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProdutoEntity {
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produto_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private ProdutoEntity produto;
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}
