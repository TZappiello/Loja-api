package com.zap.lojazap.domain.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zap.lojazap.domaindois.entities.Endereco;

public abstract class PedidoMixin {

	@JsonIgnore
	private Endereco endereco;
}
