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

import com.zap.lojazap.api.DTO.EstadoDTO;
import com.zap.lojazap.api.DTO.ProdutoDTO;
import com.zap.lojazap.api.assember.ProdutoModelAssembler;
import com.zap.lojazap.api.assember.ProdutoModelInputAssembler;
import com.zap.lojazap.api.assember.RestauranteModelInputAssembler;
import com.zap.lojazap.api.input.EstadoIdInput;
import com.zap.lojazap.api.input.ProdutoInput;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.ProdutoRepository;
import com.zap.lojazap.domaindois.service.CadastroProdutosService;
import com.zap.lojazap.domaindois.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutosService cadastroProdutoService;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoModelInputAssembler produtoModelInputAssembler;
	
	
	@GetMapping
	public List<ProdutoDTO> listarTodos(@PathVariable Long restauranteId) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		List<ProdutoEntity> todosprodutos = produtoRepository.findByRestaurante(restaurante);
		
		return produtoModelAssembler.toCollectionDTO(todosprodutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO listaPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		ProdutoEntity produto = cadastroProdutoService.buscarSeTiver(restauranteId, produtoId);
				
		return produtoModelAssembler.toDTO(produto);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoIdInput) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		ProdutoEntity produto = produtoModelInputAssembler.toDTOObject(produtoIdInput);
		produto.setRestaurante(restaurante);
		
		produto = cadastroProdutoService.cadastrar(produto);
		
		return produtoModelAssembler.toDTO(produto);
		
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoIdInput) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		ProdutoEntity produto = cadastroProdutoService.buscarSeTiver(restauranteId, produtoId);
		
		produtoModelInputAssembler.copyToDtoObject(produtoIdInput, produto);
		
//		ProdutoEntity produto1 = produtoModelInputAssembler.toDTOObject(produtoIdInput);
		
		return produtoModelAssembler.toDTO(cadastroProdutoService.cadastrar(produto));
	}
	
}
		//@PutMapping("/{id}")
		//public EstadoDTO atualizar(@PathVariable Long id, @RequestBody @Valid EstadoIdInput estadoIdInput) {
		//	EstadoEntity estadoAtual = cadastroEstados.buscarSeTiver(id);
		//
		//	estadoModelInputAssembler.copyToDtoObject(estadoIdInput, estadoAtual);
		//
		//	return estadoModelAssembler.toDTO(cadastroEstados.adicionar(estadoAtual));
