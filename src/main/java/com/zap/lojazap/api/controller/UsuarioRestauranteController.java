package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.UsuarioDTO;
import com.zap.lojazap.api.assember.UsuarioModelAssembler;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class UsuarioRestauranteController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@GetMapping
	public List<UsuarioDTO> listar(@PathVariable Long restauranteId) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		return usuarioModelAssembler.toCollectionDTO(restaurante.getUsuarios());
	}
	
//	@DeleteMapping("{formaPagamentoId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
//		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
//	}
//
//	@PutMapping("/{formaPagamentoId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
//		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
//	}
}
