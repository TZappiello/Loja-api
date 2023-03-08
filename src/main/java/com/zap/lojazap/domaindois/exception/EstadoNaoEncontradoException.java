package com.zap.lojazap.domaindois.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException( String mensagem) {
		super( mensagem);
	}

	public EstadoNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de Estado com código %d", id));
	}
}
