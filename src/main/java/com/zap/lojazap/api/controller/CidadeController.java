package com.zap.lojazap.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.EstadoNaoEncontradoException;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exceptionhandler.Problema;
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
	public List<CidadeEntity> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public CidadeEntity porId(@PathVariable Long id) {
		
		return cadastroService.buscarSeTiver(id);
		
//		Optional<CidadeEntity> cidade = cidadeRepository.findById(id);
//		if (cidade.isPresent()) {
//			return ResponseEntity.ok(cidade.get());
//		}
//
//		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeEntity adicionar(@RequestBody CidadeEntity cidade) {
		try {
			return cadastroService.cadastrar(cidade);

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public CidadeEntity atualizar(@PathVariable Long id, @RequestBody CidadeEntity cidade){
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
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?>  tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?>  tratarNegocioException(NegocioException e){
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
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

}
