package com.zap.lojazap.domaindois.exception;

public class ProdutoNaoEncontadoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontadoException( String mensagem) {
		super( mensagem);
	}

	public ProdutoNaoEncontadoException(Long restauranteId, Long id) {
		this(String.format("Não existe um cadastro de produto com código %d para o restaurante com o código %d", id, restauranteId));
	}
}
