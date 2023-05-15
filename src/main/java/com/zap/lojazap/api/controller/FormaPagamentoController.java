package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.FormaPagamentoDTO;
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
	
	@GetMapping
	public List<FormaPagamentoDTO> listar(){
		return toCollectionDTO(formaPagamentoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public FormaPagamentoDTO porid(@PathVariable Long id) {
		FormaPagamentoEntity entity = cadastroFormaPagamentoService.buscarSeTiver(id);
		return toDTO(entity);
	}
	
	
	private FormaPagamentoDTO toDTO(FormaPagamentoEntity formaPagamentoEntity) {
		FormaPagamentoDTO dto = new FormaPagamentoDTO();
		
		dto.setId(formaPagamentoEntity.getId());
		dto.setDescricao(formaPagamentoEntity.getDescricao());
		return dto;
		
	}
	
	private List<FormaPagamentoDTO> toCollectionDTO(List<FormaPagamentoEntity> listaPagamento){
		return listaPagamento.stream()
			.map(lista ->toDTO(lista))
			.collect(Collectors.toList());
		
	}
}
	
