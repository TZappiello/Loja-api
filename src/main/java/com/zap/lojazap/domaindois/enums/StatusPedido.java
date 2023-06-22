package com.zap.lojazap.domaindois.enums;

public enum StatusPedido {
	
	CRIADO("Criado"), 
	CONFIRMADO("Confirmado"),  
	ENTREGUE("Entregue"), 
	CANCELADO("Cancelado");


	private String descricao;

	private StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
