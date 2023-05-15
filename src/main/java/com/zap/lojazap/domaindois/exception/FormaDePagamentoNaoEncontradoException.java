package com.zap.lojazap.domaindois.exception;

public class FormaDePagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public FormaDePagamentoNaoEncontradoException( String mensagem) {
		super( mensagem);
	}

	public FormaDePagamentoNaoEncontradoException(Long id) {
		this(String.format("Não existe uma Forma de Pagamento com código %d", id));
	}
}
