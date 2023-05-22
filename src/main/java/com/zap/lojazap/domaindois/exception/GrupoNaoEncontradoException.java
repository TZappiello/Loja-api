package com.zap.lojazap.domaindois.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException( String mensagem) {
		super( mensagem);
	}

	public GrupoNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de Grupo com código %d", id));
	}
}
