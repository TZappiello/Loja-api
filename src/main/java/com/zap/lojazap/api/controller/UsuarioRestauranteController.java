package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.assember.UsuarioModelAssembler;
import com.zap.lojazap.api.dto.UsuarioDTO;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
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
		Set<UsuarioEntity> restaurantes = restaurante.getUsuarios();
		
		return usuarioModelAssembler.toCollectionDTO(restaurantes);
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarUsuario(restauranteId, usuarioId);
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarUsuario(restauranteId, usuarioId);
	}
	
}
