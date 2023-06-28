package com.zap.lojazap.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.assember.FormaPagamentoModelAssembler;
import com.zap.lojazap.api.assember.FormaPagamentoModelInputAssembler;
import com.zap.lojazap.api.dto.EstadoDTO;
import com.zap.lojazap.api.dto.FormaPagamentoDTO;
import com.zap.lojazap.api.input.EstadoIdInput;
import com.zap.lojazap.api.input.FormaPagamentoInput;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
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
	public List<FormaPagamentoDTO> listar() {
		return formaPagamentoModelAssembler.toCollectionDTO(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{id}")
	public FormaPagamentoDTO porid(@PathVariable Long id) {
		FormaPagamentoEntity entity = cadastroFormaPagamentoService.buscarSeTiver(id);
		return formaPagamentoModelAssembler.toDTO(entity);
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
