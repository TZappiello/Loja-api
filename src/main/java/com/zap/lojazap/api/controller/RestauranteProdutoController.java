package com.zap.lojazap.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.assember.ProdutoModelAssembler;
import com.zap.lojazap.api.assember.ProdutoModelInputAssembler;
import com.zap.lojazap.api.dto.ProdutoDTO;
import com.zap.lojazap.api.input.ProdutoInput;
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
	public List<ProdutoDTO> listarTodos(@PathVariable Long restauranteId,
			@RequestParam(required = false) boolean ativo) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		List<ProdutoEntity> todosprodutos = null;
		if(ativo) {
			todosprodutos = produtoRepository.findByRestaurante(restaurante);
		}else {
			todosprodutos = produtoRepository.findByAtivo(restaurante);
		}
		
		return produtoModelAssembler.toCollectionDTO(todosprodutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO listaPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		ProdutoEntity produto = cadastroProdutoService.buscarSeTiver(restauranteId, produtoId);
				
		return produtoModelAssembler.toDTO(produto);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		RestauranteEntity restaurante = cadastroRestaurante.buscarSeTiver(restauranteId);
		
		ProdutoEntity produto = produtoModelInputAssembler.toDTOObject(produtoInput);
		produto.setRestaurante(restaurante);
		
		produto = cadastroProdutoService.cadastrar(produto);
		
		return produtoModelAssembler.toDTO(produto);
		
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		
		ProdutoEntity produto = cadastroProdutoService.buscarSeTiver(restauranteId, produtoId);
		
		produtoModelInputAssembler.copyToDtoObject(produtoInput, produto);
		
		produto = cadastroProdutoService.cadastrar(produto);
		
		return produtoModelAssembler.toDTO(produto);
	}
	
}
