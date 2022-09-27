package com.zap.lojazap.model.exception;

public class ErroAtenticacao extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ErroAtenticacao(String msg) {
		super(msg);
	}

}
