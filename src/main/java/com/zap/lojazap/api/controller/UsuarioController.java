package com.zap.lojazap.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zap.lojazap.api.assember.UsuarioModelAssembler;
import com.zap.lojazap.api.assember.UsuarioModelInputAssembler;
import com.zap.lojazap.api.dto.UsuarioDTO;
import com.zap.lojazap.api.input.UsuarioIdInput;
import com.zap.lojazap.api.input.UsuarioInputAtualizar;
import com.zap.lojazap.api.input.UsuarioInputAtualizarSenha;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exception.UsuarioNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.UsuarioRepository;
import com.zap.lojazap.domaindois.service.CadastroUsuarioService;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioModelInputAssembler usuarioModelInputAssembler;
	
	@GetMapping
	public List<UsuarioDTO> buscarTodos(){
		return usuarioModelAssembler.toCollectionDTO(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UsuarioDTO buscarPoId(@PathVariable Long id) {
		
		return usuarioModelAssembler.toDTO(cadastroUsuarioService.buscarSeTiver(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO adicionar(@RequestBody @Valid UsuarioIdInput usuarioIdInput) {
		try {
			UsuarioEntity usuario = usuarioModelInputAssembler.toDTOObject(usuarioIdInput);
			return usuarioModelAssembler.toDTO(cadastroUsuarioService.cadastrar(usuario));			
			
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public UsuarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInputAtualizar usuario) {
		try {
			UsuarioEntity entity = cadastroUsuarioService.buscarSeTiver(id);
			
			usuarioModelInputAssembler.copyToDtoObjectUsuarioSemSenha(usuario, entity);
			
			return usuarioModelAssembler.toDTO(cadastroUsuarioService.cadastrar(entity));
			
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public UsuarioDTO atualizarSenha(@PathVariable Long id, @RequestBody @Valid UsuarioInputAtualizarSenha atualizarSenha) {
		try {
			
			UsuarioEntity entity = cadastroUsuarioService.AtualizarSenha(id, atualizarSenha);
			
			return usuarioModelAssembler.toDTO(entity);
			
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
