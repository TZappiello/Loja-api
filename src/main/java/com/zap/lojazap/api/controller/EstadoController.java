package com.zap.lojazap.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.model.EstadoEntity;
import com.zap.lojazap.domaindois.repository.EstadoRepository;
import com.zap.lojazap.domaindois.service.CadastroEstadosService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadosService cadastroEstados;
	
	@GetMapping
	public List<EstadoEntity> listar(){
		return estadoRepository.todas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstadoEntity> porId(@PathVariable Long id){
		EstadoEntity estado = estadoRepository.porId(id);
		if(estado != null) {
			return ResponseEntity.ok().body(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<EstadoEntity> adicionar (@RequestBody EstadoEntity estados){
		estados = cadastroEstados.adicionar(estados);
		return ResponseEntity.created(null).body(estados);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstadoEntity> atualizar(@PathVariable Long id, @RequestBody EstadoEntity estados){
		EstadoEntity estadoAtual = estadoRepository.porId(id);
		
		if(estadoAtual != null) {
			BeanUtils.copyProperties(estados, estadoAtual, "id");
			cadastroEstados.adicionar(estadoAtual);
			
			return ResponseEntity.created(null).body(estadoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
}



















