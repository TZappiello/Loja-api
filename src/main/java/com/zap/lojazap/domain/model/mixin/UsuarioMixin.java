package com.zap.lojazap.domain.model.mixin;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class UsuarioMixin {

	@JsonIgnore
	private OffsetDateTime dataCadastro;
}
