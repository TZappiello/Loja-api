package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.model.CidadeEntity;
import com.zap.lojazap.domaindois.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping
	public List<CidadeEntity> listar() {
		return cidadeRepository.todas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CidadeEntity> porId(@PathVariable Long id){
		CidadeEntity cidade = cidadeRepository.porId(id);
		if(cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		
		return ResponseEntity.notFound().build();
	}

}
