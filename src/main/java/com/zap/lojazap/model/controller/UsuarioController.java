package com.zap.lojazap.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@GetMapping
	public String mostrarDados() {
		return "Ola estou treinando spring";
	}

}
