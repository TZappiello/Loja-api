package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.CidadeDTO;
import com.zap.lojazap.api.DTO.EstadoDTO;
import com.zap.lojazap.api.input.CidadeIdInput;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EstadoNaoEncontradoException;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.repository.CidadeRepository;
import com.zap.lojazap.domaindois.service.CadastroCidadesService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadesService cadastroService;

	@GetMapping
	public List<CidadeDTO> listar() {
		return toCollectionDTO(cidadeRepository.findAll());
	}

	@GetMapping("/{id}")
	public CidadeDTO porId(@PathVariable Long id) {
		CidadeEntity cidade = cadastroService.buscarSeTiver(id);
		
		return toDTO(cidade);
		
//		Optional<CidadeEntity> cidade = cidadeRepository.findById(id);
//		if (cidade.isPresent()) {
//			return ResponseEntity.ok(cidade.get());
//		}
//
//		return ResponseEntity.notFound().build();
	}

	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody @Valid CidadeIdInput cidadeIdInput) {
		
		try {
			CidadeEntity cidade = toDTOEntity(cidadeIdInput);
			
			return toDTO(cadastroService.cadastrar(cidade));

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public CidadeEntity atualizar(@PathVariable Long id, @RequestBody @Valid CidadeEntity cidade){
		try {
			CidadeEntity cidadeEntity = cadastroService.buscarSeTiver(id);
			BeanUtils.copyProperties(cidade, cidadeEntity, "id");

			return cadastroService.cadastrar(cidadeEntity);
			
		} catch (EstadoNaoEncontradoException e) {
			 throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CidadeEntity> remover(@PathVariable Long id) {
		try {
			cadastroService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);

		} catch (EntidadeEmUsoException e) {
			throw new EntidadeEmUsoException(e.getMessage());

		}
	}
	
	/*
	try {
		Optional<CidadeEntity> cidadeId = cidadeRepository.findById(id);
		
		if(cidadeId.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		if(cidadeId.isPresent()) {
			BeanUtils.copyProperties(cidade, cidadeId.get(), "id");
			cadastroService.cadastrar(cidadeId.get());
		}
		
		return ResponseEntity.ok(cidade);	
		
	} catch (EntidadeNaoEncontradaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

	} catch (EntidadeEmUsoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

	}*/

	private CidadeDTO toDTO(CidadeEntity cidade) {
		EstadoDTO estadoDTO = new EstadoDTO();
		estadoDTO.setId(cidade.getEstado().getId());
		estadoDTO.setNome(cidade.getEstado().getNome());
		
		CidadeDTO cidadeDTO = new CidadeDTO();
		cidadeDTO.setId(cidade.getId());
		cidadeDTO.setNome(cidade.getNome());
		cidadeDTO.setEstado(estadoDTO);
		
		return cidadeDTO;
	}
	
	private List<CidadeDTO> toCollectionDTO(List<CidadeEntity> cidades){
		
		return cidades.stream()
				.map(cidade -> toDTO(cidade))
				.collect(Collectors.toList());
		
	}
	
	
	private CidadeEntity toDTOEntity (CidadeIdInput cidadeIdInput) {
		CidadeEntity cidadeEntity = new CidadeEntity();
		cidadeEntity.setNome(cidadeIdInput.getNome());

		EstadoEntity estadoEntity = new EstadoEntity();
		estadoEntity.setId(cidadeIdInput.getEstado().getId());
		
		cidadeEntity.setEstado(estadoEntity);
		return cidadeEntity;
		
	}
}
