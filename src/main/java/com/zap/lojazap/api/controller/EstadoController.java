package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.DTO.EstadoDTO;
import com.zap.lojazap.api.assember.EstadoModelAssembler;
import com.zap.lojazap.api.assember.EstadoModelInputAssembler;
import com.zap.lojazap.api.input.EstadoIdInput;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.EstadoRepository;
import com.zap.lojazap.domaindois.service.CadastroEstadosService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadosService cadastroEstados;

	@Autowired
	private EstadoModelInputAssembler estadoModelInputAssembler;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@GetMapping
	public List<EstadoDTO> listar() {
		return toColectorDTO(estadoRepository.findAll());
	}

	@GetMapping("/{id}")
	public EstadoDTO porId(@PathVariable Long id) {

		return toDTO(cadastroEstados.buscarSeTiver(id));
//		Optional<EstadoEntity> estado = estadoRepository.findById(id);
//		
//		if(estado.isPresent()) {
//			return ResponseEntity.ok().body(estado.get());
//		}
//		
//		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public EstadoDTO adicionar(@RequestBody @Valid EstadoIdInput estadoIdInput) {
		EstadoEntity estados = estadoModelInputAssembler.toDTOObject(estadoIdInput);

		return toDTO(cadastroEstados.adicionar(estados));
	}

	@PutMapping("/{id}")
	public EstadoDTO atualizar(@PathVariable Long id, @RequestBody @Valid EstadoIdInput estadoIdInput) {
		EstadoEntity estadoAtual = cadastroEstados.buscarSeTiver(id);

		estadoModelInputAssembler.copyToDtoObject(estadoIdInput, estadoAtual);

		return estadoModelAssembler.toDTO(cadastroEstados.adicionar(estadoAtual));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remover(@PathVariable Long id) {
		try {
			cadastroEstados.excluir(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		}

	}

	public EstadoDTO toDTO(EstadoEntity estadoEntity) {
		EstadoDTO estadoDto = new EstadoDTO();
		estadoDto.setId(estadoEntity.getId());
		estadoDto.setNome(estadoEntity.getNome());

		return estadoDto;
	}

	public List<EstadoDTO> toColectorDTO(List<EstadoEntity> estados) {
		return estados.stream().map(estado -> toDTO(estado)).collect(Collectors.toList());
	}

	private EstadoEntity toDTOEntity(EstadoIdInput estadoIdInput) {
		EstadoEntity estadoEntity = new EstadoEntity();

//		estadoEntity.setId(estadoIdInput.getId());

		return estadoEntity;
	}
}
