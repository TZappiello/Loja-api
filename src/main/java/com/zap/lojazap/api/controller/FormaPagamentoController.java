package com.zap.lojazap.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.FormaPagamentoDTO;
import com.zap.lojazap.api.assember.FormaPagamentoModelAssembler;
import com.zap.lojazap.api.assember.FormaPagamentoModelInputAssembler;
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
}

	//@PostMapping
	//public EstadoDTO adicionar(@RequestBody @Valid EstadoIdInput estadoIdInput) {
	//	EstadoEntity estados = estadoModelInputAssembler.toDTOObject(estadoIdInput);
	//
	//	return estadoModelAssembler.toDTO(cadastroEstados.adicionar(estados));
	//}

