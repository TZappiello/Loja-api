package com.zap.lojazap.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.zap.lojazap.domaindois.entities.PedidoEntity;
import com.zap.lojazap.domaindois.filter.PedidoFilter;

public class PedidoSpec {

	public static Specification<PedidoEntity> usandoFiltro(PedidoFilter filtro) {

		return (root, query, builder) -> {
			if(PedidoEntity.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if(filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
			}
			
			if(filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			
			if(filtro.getDataCriacao() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
						filtro.getDataCriacao()));
			}
			
			if(filtro.getDataFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), 
						filtro.getDataFim()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
