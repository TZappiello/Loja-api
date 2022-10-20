package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.model.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<CozinhaEntity> listar() {
		return cozinhaRepository.todas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CozinhaEntity> porId(@PathVariable Long id) {
		CozinhaEntity cozinha = cozinhaRepository.porId(id);

		if (cozinha == null) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).b uild();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
	}
	
//	@GetMapping("/{id}")
//	public CozinhaEntity porId(@PathVariable Long id){
//		return cozinhaRepository.porId(id);
//	}
	
	@PostMapping
	public ResponseEntity<CozinhaEntity> adicionar(@RequestBody CozinhaEntity cozinha){
				cozinha = cozinhaRepository.adicionar(cozinha);
		return ResponseEntity.created(null).body(cozinha);
	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public CozinhaEntity adicionar(@RequestBody CozinhaEntity cozinha){
//		return cozinhaRepository.adicionar(cozinha);
//	}

}
