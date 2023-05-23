package com.zap.lojazap.domaindois.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public UsuarioNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de Usuário com código %d", id));
	}

}
