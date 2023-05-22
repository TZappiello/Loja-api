package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.GrupoDTO;
import com.zap.lojazap.api.assember.GrupoModelAssembler;
import com.zap.lojazap.api.assember.GrupoModelInputAssembler;
import com.zap.lojazap.api.input.GrupoIdInput;
import com.zap.lojazap.domaindois.entities.GrupoEntity;
import com.zap.lojazap.domaindois.exception.GrupoNaoEncontradoException;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.repository.GrupoRepository;
import com.zap.lojazap.domaindois.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private GrupoModelInputAssembler grupoModelInputAssembler;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@GetMapping
	public List<GrupoDTO> listar(){
		return grupoModelAssembler.toCollectionDTO(grupoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public GrupoDTO buscarPorId(@PathVariable Long id) {
		
		return grupoModelAssembler.toDTO(cadastroGrupo.buscarSeTiver(id));
	}
	
	@PostMapping
	public GrupoDTO salvar(@RequestBody @Valid GrupoIdInput grupo) {
		try {
			
			GrupoEntity entity = grupoModelInputAssembler.toDTOObject(grupo);
			
			return grupoModelAssembler.toDTO(cadastroGrupo.cadastrar(entity));
			
		} catch (GrupoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
}
