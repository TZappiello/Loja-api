package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.ProdutoDTO;
import com.zap.lojazap.api.assember.ProdutoModelAssembler;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.service.CadastroProdutosService;
import com.zap.lojazap.domaindois.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private CadastroProdutosService cadastroProdutoService;
	
	@GetMapping
	public List<ProdutoDTO> listarTodos(@PathVariable Long restauranteId) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		return produtoModelAssembler.toCollectionDTO(restaurante.getProdutos());
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO listaPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiverProdutos(restauranteId, produtoId);
				
//		ProdutoEntity produto = cadastroProdutoService.buscarSeTiver(produtoId);
		
		return produtoModelAssembler.toDTO(restaurante.getProdutos().get(0));
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
	}
}
