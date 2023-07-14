package com.zap.lojazap.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.zap.lojazap.api.assember.FormaPagamentoModelAssembler;
import com.zap.lojazap.api.assember.FormaPagamentoModelInputAssembler;
import com.zap.lojazap.api.dto.FormaPagamentoDTO;
import com.zap.lojazap.api.input.FormaPagamentoInput;
import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.repository.FormaPagamentoRepository;
import com.zap.lojazap.domaindois.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/forma_pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoModelInputAssembler formaPagamentoModelInputAssembler;

	@GetMapping
	public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamentoDTO> formasPagamentos = formaPagamentoModelAssembler
				.toCollectionDTO(formaPagamentoRepository.findAll());

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)) // COLOCANDO CACHE NO METODO
				.eTag(eTag)																			// PODENDO SETAR O TEMPO
				.body(formasPagamentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamentoDTO> porid(@PathVariable Long id, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag ="0";
		
		 OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();
		
		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		FormaPagamentoEntity entity = cadastroFormaPagamentoService.buscarSeTiver(id);
		var formaPagamentoPorid = formaPagamentoModelAssembler.toDTO(entity);

		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formaPagamentoPorid);
	}

	@PostMapping
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamentoEntity pagamentoEntity = formaPagamentoModelInputAssembler.toDTOObject(formaPagamentoInput);

		return formaPagamentoModelAssembler.toDTO(cadastroFormaPagamentoService.cadastrar(pagamentoEntity));

	}

	@PutMapping("/{id}")
	public FormaPagamentoDTO atualizar(@PathVariable Long id,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamentoEntity entity = cadastroFormaPagamentoService.buscarSeTiver(id);

		formaPagamentoModelInputAssembler.copyToDtoObject(formaPagamentoInput, entity);

		return formaPagamentoModelAssembler.toDTO(cadastroFormaPagamentoService.cadastrar(entity));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroFormaPagamentoService.remover(id);

	}

}
