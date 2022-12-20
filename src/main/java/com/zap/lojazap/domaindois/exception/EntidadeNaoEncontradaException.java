package com.zap.lojazap.domaindois.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeNaoEncontradaException extends ResponseStatusException {
	private static final long serialVersionUID = 1L;
	
	
	public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}

	public EntidadeNaoEncontradaException(String mensagem) {
		this(HttpStatus.NOT_FOUND, mensagem);
	}

}
