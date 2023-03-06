package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.Optional;

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
	
	@GetMapping
	public List<EstadoEntity> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public EstadoEntity porId(@PathVariable Long id){
		
		return cadastroEstados.buscarSeTiver(id);
//		Optional<EstadoEntity> estado = estadoRepository.findById(id);
//		
//		if(estado.isPresent()) {
//			return ResponseEntity.ok().body(estado.get());
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<EstadoEntity> adicionar (@RequestBody EstadoEntity estados){
		estados = cadastroEstados.adicionar(estados);
		return ResponseEntity.created(null).body(estados);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstadoEntity> atualizar(@PathVariable Long id, @RequestBody EstadoEntity estados){
		Optional<EstadoEntity> estadoAtual = estadoRepository.findById(id);
		
		if(estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estados, estadoAtual.get(), "id");
			cadastroEstados.adicionar(estadoAtual.get());
			
			return ResponseEntity.created(null).body(estadoAtual.get());
		}
		
		return ResponseEntity.notFound().build();
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
}



















