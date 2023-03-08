package com.zap.lojazap.domaindois.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)   	//essa exception pode mandar varios status http no corpo
public abstract class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException( String mensagem) {
		super( mensagem);
	}

    /*public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}
	
	public EntidadeNaoEncontradaException(String mensagem) {
		this(HttpStatus.NOT_FOUND, mensagem);
	} */

}
