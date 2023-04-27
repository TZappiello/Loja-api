package com.zap.lojazap.domain.model.mixin;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.Endereco;
import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;

public abstract class RestauranteMixin   {


	//@JsonIgnore // não vai mostra nenhuma cozinha
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private CozinhaEntity cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
//	@JsonIgnore
	private OffsetDateTime dataCadastro;

//	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamentoEntity> formasPagamento = new ArrayList<>();
	
	@JsonIgnore // ignora a serialização da id.
	private List<ProdutoEntity> produtos = new ArrayList<>();
}
