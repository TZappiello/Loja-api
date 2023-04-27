package com.zap.lojazap.domain.model.mixin;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class UsuarioMixin {

	@JsonIgnore
	private LocalDateTime dataCadastro;
}
