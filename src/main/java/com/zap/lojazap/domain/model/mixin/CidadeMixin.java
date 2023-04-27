package com.zap.lojazap.domain.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zap.lojazap.domaindois.entities.EstadoEntity;

public abstract class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private EstadoEntity estado;
}
