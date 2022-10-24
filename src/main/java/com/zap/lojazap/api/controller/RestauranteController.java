package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
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
	public List<RestauranteEntity> listar() {
		return restauranteRepository.todas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<RestauranteEntity> porId(@PathVariable Long id) {
		RestauranteEntity restaurante = restauranteRepository.porId(id);

		if (restaurante == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(restaurante);
	}

	@PostMapping
	public ResponseEntity<Object> adicionar(@RequestBody RestauranteEntity restaurante) {
		try {
			restaurante = cadastroRestaurante.cadastrar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody RestauranteEntity restaurante) {
		try {
			RestauranteEntity restauranteId = restauranteRepository.porId(id);

//			if (restauranteId == null) {
//				throw new EntidadeEmUsoException(
//						String.format("O código do restaurante não pode ser %d, precisa de um restaurante válido para poder atualizar", restauranteId));
//			}

			if (restauranteId != null) {
				BeanUtils.copyProperties(restaurante, restauranteId, "id");
				cadastroRestaurante.cadastrar(restauranteId);
			}

			return ResponseEntity.ok(restaurante);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		}
	}
}
