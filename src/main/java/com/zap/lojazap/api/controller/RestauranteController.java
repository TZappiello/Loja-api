package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.model.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;
import com.zap.lojazap.domaindois.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<RestauranteEntity> listar(){
		return restauranteRepository.todas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestauranteEntity> porId(@PathVariable Long id){
		RestauranteEntity restaurante = restauranteRepository.porId(id);
		
		if(restaurante == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(restaurante);
	}
	
	@PostMapping
	public ResponseEntity<Object> adicionar(@RequestBody RestauranteEntity restaurante){
		try {
			restaurante = cadastroRestaurante.cadstrar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	
	
	
	

}
