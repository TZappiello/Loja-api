package com.zap.lojazap.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.FormaPagamentoDTO;
import com.zap.lojazap.api.assember.FormaPagamentoModelAssembler;
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

	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		return formaPagamentoModelAssembler.toCollectionDTO(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{id}")
	public FormaPagamentoDTO porid(@PathVariable Long id) {
		FormaPagamentoEntity entity = cadastroFormaPagamentoService.buscarSeTiver(id);
		return formaPagamentoModelAssembler.toDTO(entity);
	}

	public FormaPagamentoDTO adicionar(@RequestParam @Valid FormaPagamentoInput formaPagamentoInput ) {
		
	}
}


		//@PostMapping
		//public RestauranteDTO adicionar(@RequestBody @Valid // @Validated(Groups.CozinhaId.class)
		//RestauranteInput restauranteInput) {
		//	try {
		//		RestauranteEntity restaurante = restauranteModelInputAssembler.toDTOObject(restauranteInput);
		//
		//		return restauranteModelAssembler.toDTO(cadastroRestaurante.cadastrar(restaurante));
		//
		//	} catch (EntidadeNaoEncontradaException e) {
		////		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		////		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		//		throw new NegocioException(e.getMessage());
		//	}
		//}
