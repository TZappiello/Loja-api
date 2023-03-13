package com.zap.lojazap.domaindois.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade Não encontrada");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://loja-api.com.br" + path;
		this.title = title;
	}
}
