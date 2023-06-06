package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.PermissaoDTO;
import com.zap.lojazap.api.assember.PermissaoModelAssembler;
import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping
	public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
		GrupoEntity grupo = cadastroGrupo.buscarSeTiver(grupoId);
		
		return permissaoModelAssembler.toCollectionDTO(grupo.getPermissoes());
	}

}
