package com.zap.lojazap.domaindois.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException( String mensagem) {
		super( mensagem);
	}

	public PermissaoNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de Permissão com código %d", id));
	}
}
