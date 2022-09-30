package com.zap.lojazap.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zap.lojazap.model.dto.LancamentoDTO;
import com.zap.lojazap.model.entity.Lancamento;
import com.zap.lojazap.model.entity.Usuario;
import com.zap.lojazap.model.enums.StatusLancamento;
import com.zap.lojazap.model.enums.TipoLancamento;
import com.zap.lojazap.model.exception.RegraDeNegocioException;
import com.zap.lojazap.model.service.LancamentoService;
import com.zap.lojazap.model.service.UsuarioService;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody LancamentoDTO dto) {
		
		return null;
		
	}
	
	private Lancamento converter(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = usuarioService.obterPorId(dto.getUsuario())
		.orElseThrow(()-> new RegraDeNegocioException("Usuário não encontrado para o Id informado!"));
		
		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		
		return lancamento;
	}
}

















