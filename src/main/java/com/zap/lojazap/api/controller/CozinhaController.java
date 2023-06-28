package com.zap.lojazap.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.zap.lojazap.api.assember.CozinhaModelAssembler;
import com.zap.lojazap.api.assember.CozinhaModelInputAssembler;
import com.zap.lojazap.api.dto.CozinhaDTO;
import com.zap.lojazap.api.input.CozinhaIdInput;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;
import com.zap.lojazap.domaindois.service.CadastroCozinhaService;

/**
 * @author JARVIZ
 *
 */
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaModelInputAssembler cozinhaModelInputAssembler;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Page<CozinhaDTO> listar(@PageableDefault(size = 2) Pageable pageable) {  // sort = "nome" pode ordenar assim setando o atributo
	
		Page<CozinhaEntity> cozinhasPage = cozinhaRepository.findAll(pageable);

		List<CozinhaDTO> cozinhasDTO = cozinhaModelAssembler.toCollectionDTO(cozinhasPage.getContent());
		
		Page<CozinhaDTO> cozinhaDtoPage = new PageImpl<>(cozinhasDTO, pageable, cozinhasPage.getTotalElements());
		
		return cozinhaDtoPage;
	}

	@GetMapping("/por-nome")
	public List<CozinhaDTO> listarPorNome(@RequestParam String nome) {
		return cozinhaModelAssembler.toCollectionDTO(cozinhaRepository.findTodasBynomeContaining(nome));
	}

	@GetMapping("/por-nome-completo")
	public Optional<CozinhaEntity> listarPorNomeCompleto(@RequestParam String nome) {
		return cozinhaRepository.findNomeCompletoByNome(nome);
	}

	@GetMapping("/{id}")
	public CozinhaDTO porId(@PathVariable Long id) {
		CozinhaEntity cozinhaEntity = cadastroCozinha.buscarSeTiver(id);
		return cozinhaModelAssembler.toDTO(cozinhaEntity);
	}

	@PostMapping
	public CozinhaDTO adicionar(@RequestBody @Valid CozinhaIdInput cozinhaIput) {
		CozinhaEntity cozinhaEntity = cozinhaModelInputAssembler.toDTOObject(cozinhaIput);
		cozinhaEntity = cadastroCozinha.adicionar(cozinhaEntity);
		
		return cozinhaModelAssembler.toDTO(cozinhaEntity);
	}

	@PutMapping("/{id}")
	public CozinhaDTO atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaIdInput cozinhaIput) {

		CozinhaEntity cozinhaAtual = cadastroCozinha.buscarSeTiver(id);
		cozinhaModelInputAssembler.copyToDtoObject(cozinhaIput, cozinhaAtual);

		return cozinhaModelAssembler.toDTO(cadastroCozinha.adicionar(cozinhaAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCozinha.excluir(id);

	}

	/*
	 * @GetMapping("/{id}") public CozinhaEntity porId(@PathVariable Long id){
	 * return cozinhaRepository.porId(id); }
	 * 
	 * @PostMapping
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public CozinhaEntity
	 * adicionar(@RequestBody CozinhaEntity cozinha){ return
	 * cozinhaRepository.adicionar(cozinha); }
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<CozinhaEntity>
	 * atualizar(@PathVariable Long id, @RequestBody CozinhaEntity cozinha) {
	 * 
	 * Optional<CozinhaEntity> cozinhaAtual = cozinhaRepository.findById(id);
	 * 
	 * if (cozinhaAtual.isPresent()) { BeanUtils.copyProperties(cozinha,
	 * cozinhaAtual.get(), "id"); cadastroCozinha.adicionar(cozinhaAtual.get());
	 * 
	 * return ResponseEntity.ok(cozinhaAtual.get());
	 * 
	 * CozinhaEntity cozinhaSalva = cadastroCozinha.adicionar(cozinhaAtual.get());
	 * return ResponseEntity.ok(cozinhaSalva); pode ser assim tbm }
	 * 
	 * return ResponseEntity.notFound().build(); }
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<CozinhaEntity>
	 * atualizar(@PathVariable Long id, @RequestBody CozinhaEntity cozinha) {
	 * 
	 * CozinhaEntity cozinhaAtual = cozinhaRepository.porId(id);
	 * 
	 * cozinhaAtual.setNome(cozinha.getNome()); cozinhaAtual =
	 * cozinhaRepository.adicionar(cozinhaAtual);
	 * 
	 * return ResponseEntity.ok(cozinhaAtual); }
	 * 
	 * 
	 * @DeleteMapping("/{id}") public ResponseEntity<CozinhaEntity>
	 * remover(@PathVariable Long id) { try { cadastroCozinha.excluir(id); return
	 * ResponseEntity.noContent().build();
	 * 
	 * } catch (EntidadeNaoEncontradaException e) { return
	 * ResponseEntity.notFound().build();
	 * 
	 * } catch (EntidadeEmUsoException e) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).build();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @DeleteMapping("/{id}") public void remover(@PathVariable Long id) {
	 * CozinhaEntity cozinha = cozinhaRepository.porId(id);
	 * cozinhaRepository.remover(cozinha); }
	 */

}
