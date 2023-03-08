package com.zap.lojazap.domaindois.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException( String mensagem) {
		super( mensagem);
	}

	public CidadeNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro da Cidade com código %d", id));
	}
}
