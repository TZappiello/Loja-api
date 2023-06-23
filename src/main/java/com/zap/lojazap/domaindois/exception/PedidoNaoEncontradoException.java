package com.zap.lojazap.domaindois.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String codigo) {
		super(String.format("Não existe um cadastro de pedido com código %s", codigo));
	}
}
