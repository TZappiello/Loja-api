package com.zap.lojazap.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zap.lojazap.model.dto.LancamentoDTO;
import com.zap.lojazap.model.service.LancamentoService;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;

	@PostMapping
	public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {
		
		return null;
		
	}
}
