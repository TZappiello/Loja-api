package com.zap.lojazap.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("isAuthenticated()")
@Retention(RUNTIME)
@Target(METHOD)
public @interface PodeConsultarCozinhas {

	// 	PARA ESTUDO NÃO ESTA SENDO USADA!
}
