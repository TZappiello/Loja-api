package com.zap.lojazap.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zap.lojazap.domain.model.mixin.CozinhaMixin;
import com.zap.lojazap.domain.model.mixin.PedidoMixin;
import com.zap.lojazap.domain.model.mixin.UsuarioMixin;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.entities.UsuarioEntity;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(CozinhaEntity.class, CozinhaMixin.class);
		setMixInAnnotation(PedidoEntity.class, PedidoMixin.class);
		setMixInAnnotation(UsuarioEntity.class, UsuarioMixin.class);
	}
}
