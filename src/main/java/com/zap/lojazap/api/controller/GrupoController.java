package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.GrupoDTO;
import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.repository.GrupoRepository;
import com.zap.lojazap.domaindois.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@GetMapping
	public List<GrupoDTO> listar(){
		return toCollectionDTO(grupoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public GrupoDTO buscarPorId(Long id) {
		
		return toDTO(cadastroGrupo.buscarSeTiver(id));
	}
	
	private GrupoDTO toDTO(GrupoEntity entity) {
		GrupoDTO dto = new GrupoDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		
		return dto;
	}
	
	private List<GrupoDTO> toCollectionDTO(List<GrupoEntity> grupoEntity){
		return grupoEntity.stream()
			.map(grupo -> toDTO(grupo))
			.collect(Collectors.toList());
		
	}
}
