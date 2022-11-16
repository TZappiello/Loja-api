//package com.zap.lojazap.infrastructure.repository.spec;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.springframework.data.jpa.domain.Specification;
//
//import com.zap.lojazap.domaindois.entities.RestauranteEntity;
//
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//public class RestauranteComNomeSemelhanteSpec implements Specification<RestauranteEntity>  {
//	private static final long serialVersionUID = 1L;
//	
//	private String nome;
//
//	@Override
//	public Predicate toPredicate(Root<RestauranteEntity> root, CriteriaQuery<?> query,
//			CriteriaBuilder builder) {
//		
//		
//		return builder.like(root.get("nome"), "%" + nome + "%");
//	}
//
//}
