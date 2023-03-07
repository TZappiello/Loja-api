package com.zap.lojazap.domaindois.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)   	//essa exception pode mandar varios status http no corpo
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegocioException( String mensagem) {
		super( mensagem);
	}

    /*public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}
	
	public EntidadeNaoEncontradaException(String mensagem) {
		this(HttpStatus.NOT_FOUND, mensagem);
	} */

}
