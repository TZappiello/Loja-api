package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.GrupoDTO;
import com.zap.lojazap.api.assember.GrupoModelAssembler;
import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@GetMapping
	public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
		UsuarioEntity usuario = cadastroUsuario.buscarSeTiver(usuarioId);
		List<GrupoEntity> usuarios = usuario.getGrupos();
		
		return grupoModelAssembler.toCollectionDTO(usuarios);
	}
	
}
