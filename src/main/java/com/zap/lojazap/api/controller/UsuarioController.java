package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.UsuarioDTO;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.repository.UsuarioRepository;
import com.zap.lojazap.domaindois.service.CadastroUsuarioService;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@GetMapping
	public List<UsuarioDTO> buscarTodos(){
		return toCollector(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UsuarioDTO buscarPoId(@PathVariable Long id) {
		
		return toDTO(cadastroUsuarioService.buscarSeTiver(id));
	}

	public UsuarioDTO toDTO(UsuarioEntity entity) {
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setEmail(entity.getEmail());
		
		return dto;
	}
	
	public List<UsuarioDTO> toCollector(List<UsuarioEntity> usuarios){
		return usuarios.stream()
		.map(usuario -> toDTO(usuario))
		.collect(Collectors.toList());
		
	}
}
