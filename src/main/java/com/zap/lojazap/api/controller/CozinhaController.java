package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;
import com.zap.lojazap.domaindois.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<CozinhaEntity> listar() {
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/por-nome")
	public List<CozinhaEntity> listarPorNome(@RequestParam String nome){
		return cozinhaRepository.findTodasBynomeContaining(nome);
	}

	@GetMapping("/por-nome-completo")
	public Optional<CozinhaEntity> listarPorNomeCompleto(@RequestParam String nome){
		return cozinhaRepository.findNomeCompletoByNome(nome);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CozinhaEntity> porId(@PathVariable Long id) {
		Optional<CozinhaEntity> cozinha = cozinhaRepository.findById(id);

		if (cozinha.isEmpty()) {
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).b uild();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(cozinha.get());
	}

//	@GetMapping("/{id}")
//	public CozinhaEntity porId(@PathVariable Long id){
//		return cozinhaRepository.porId(id);
//	}

	@PostMapping
	public ResponseEntity<CozinhaEntity> adicionar(@RequestBody CozinhaEntity cozinha) {
		cozinha = cadastroCozinha.adicionar(cozinha);
		return ResponseEntity.created(null).body(cozinha);
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public CozinhaEntity adicionar(@RequestBody CozinhaEntity cozinha){
//		return cozinhaRepository.adicionar(cozinha);
//	}

	@PutMapping("/{id}")
	public ResponseEntity<CozinhaEntity> atualizar(@PathVariable Long id, @RequestBody CozinhaEntity cozinha) {

		Optional<CozinhaEntity> cozinhaAtual = cozinhaRepository.findById(id);

		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			cadastroCozinha.adicionar(cozinhaAtual.get());

			return ResponseEntity.ok(cozinhaAtual.get());
			
//			CozinhaEntity cozinhaSalva = cadastroCozinha.adicionar(cozinhaAtual.get());
//			return ResponseEntity.ok(cozinhaSalva);  pode ser assim tbm
		}

		return ResponseEntity.notFound().build();
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<CozinhaEntity> atualizar(@PathVariable Long id, @RequestBody CozinhaEntity cozinha) {
//
//		CozinhaEntity cozinhaAtual = cozinhaRepository.porId(id);
//
//		cozinhaAtual.setNome(cozinha.getNome());
//		cozinhaAtual = cozinhaRepository.adicionar(cozinhaAtual);
//
//		return ResponseEntity.ok(cozinhaAtual);
//	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			cadastroCozinha.excluir(id);

	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<CozinhaEntity> remover(@PathVariable Long id) {
//		try {
//			cadastroCozinha.excluir(id);
//			return ResponseEntity.noContent().build();
//
//		} catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//
//		} catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//
//		}
//
//	}
	
//	@DeleteMapping("/{id}")
//	public void remover(@PathVariable Long  id) {
//		CozinhaEntity cozinha = cozinhaRepository.porId(id);
//		cozinhaRepository.remover(cozinha);
//	}

}
