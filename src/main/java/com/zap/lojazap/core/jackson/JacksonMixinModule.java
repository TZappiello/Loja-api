package com.zap.lojazap.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zap.lojazap.domain.model.mixin.RestauranteMixin;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(RestauranteEntity.class, RestauranteMixin.class);
	}
}
