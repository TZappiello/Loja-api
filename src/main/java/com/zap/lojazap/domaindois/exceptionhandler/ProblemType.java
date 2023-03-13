package com.zap.lojazap.domaindois.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade Não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_DE_NEGOCIO("/erro_negocio", "Violação de regra de negócio");
	
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://loja-api.com.br" + path;
		this.title = title;
	}
}
