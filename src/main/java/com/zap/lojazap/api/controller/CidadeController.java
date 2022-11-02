package com.zap.lojazap.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
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
		return cidadeRepository.todas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CidadeEntity> porId(@PathVariable Long id) {
		CidadeEntity cidade = cidadeRepository.porId(id);
		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Object> adicionar(@RequestBody CidadeEntity cidade) {
		try {
			cidade = cadastroService.cadastrar(cidade);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody CidadeEntity cidade){
		try {
			CidadeEntity cidadeId = cidadeRepository.porId(id);
			
			if(cidadeId == null) {
				return ResponseEntity.notFound().build();
			}
			
			if(cidadeId != null) {
				BeanUtils.copyProperties(cidade, cidadeId, "id");
				cadastroService.cadastrar(cidadeId);
			}
			
			return ResponseEntity.ok(cidade);	
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

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

}
