package com.zap.lojazap.domaindois.exception;

//@ResponseStatus(HttpStatus.BAD_REQUEST)   	//essa exception pode mandar varios status http no corpo
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegocioException( String mensagem) {
		super( mensagem);
	}

	public NegocioException( String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
