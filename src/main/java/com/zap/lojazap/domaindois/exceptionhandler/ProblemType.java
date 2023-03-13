package com.zap.lojazap.domaindois.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade Não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	EXCEAO_DE_NEGOCIO("/entidade-nao-existente", "Entidade não existente");
	
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://loja-api.com.br" + path;
		this.title = title;
	}
}
