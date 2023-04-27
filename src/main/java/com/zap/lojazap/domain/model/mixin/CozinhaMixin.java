package com.zap.lojazap.domain.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public abstract class CozinhaMixin {

	@JsonIgnore // ignora a serialização da id.
	private List<RestauranteEntity> restaurantes = new ArrayList<>();
}
