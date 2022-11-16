package com.zap.lojazap.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class RestauranteSpec {

	public static Specification<RestauranteEntity> comFreteGratis() {

		return (root, query, builder) ->
			builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}

	public static Specification<RestauranteEntity> comNomeSemelhante(String nome) {

		return (root, query, builder) -> 
			builder.like(root.get("nome"), "%" + nome + "%");
	}
}
