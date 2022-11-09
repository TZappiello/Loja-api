package com.zap.lojazap.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<RestauranteEntity> buscar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<RestauranteEntity> criteria = builder.createQuery(RestauranteEntity.class);
		Root<RestauranteEntity> root = criteria.from(RestauranteEntity.class);

		Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");

		Predicate taxaInicial = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);

		Predicate taxaFinal = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);

		criteria.where(nomePredicate, taxaInicial, taxaFinal);

		TypedQuery<RestauranteEntity> query = manager.createQuery(criteria);
		return query.getResultList();

	}

//	var jpql = new StringBuilder();
//	jpql.append(" FROM RestauranteEntity WHERE 0 = 0 ");
//	
//	var parametros = new HashMap<String, Object>();
//	
//	if(StringUtils.hasLength(nome)) {
//		jpql.append(" AND nome LIKE :nome ");
//		parametros.put("nome", "%" + nome + "%");
//	}
//	
//	if(taxaFreteInicial != null) {
//		jpql.append(" AND taxaFrete >= :taxaInicial ");
//		parametros.put("taxaInicial", taxaFreteInicial);
//	}
//	
//	if(taxaFreteFinal != null) {
//		jpql.append(" AND taxaFrete <= :taxaFinal ");
//		parametros.put("taxaFinal", taxaFreteFinal);
//	}
//	
//	TypedQuery<RestauranteEntity> query = manager
//			.createQuery(jpql.toString(), RestauranteEntity.class);
//	
//	parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//	
//	return query.getResultList();
//	
//	==============================================================================
//	var jpql = " FROM RestauranteEntity WHERE nome LIKE :nome "
//			+ " AND taxaFrete BETWEEN :taxaFreteInicial AND :taxaFreteFinal ";
//
//return manager.createQuery(jpql, RestauranteEntity.class)
//		.setParameter("nome", "%" + nome + "%")
//		.setParameter("taxaFreteInicial", taxaFreteInicial)
//		.setParameter("taxaFreteFinal", taxaFreteFinal)
//		.getResultList();
//	
//	==============================================================================

//	@Override
//	public List<RestauranteEntity> todas() {
//
//		return manager.createQuery(" from RestauranteEntity ", RestauranteEntity.class).getResultList();
//	}
//
//	@Override
//	public RestauranteEntity porId(Long id) {
//		return manager.find(RestauranteEntity.class, id);
//	}
//
//	@Transactional
//	@Override
//	public RestauranteEntity adicionar(RestauranteEntity restaurante) {
//		return manager.merge(restaurante);
//	}
//
//	@Transactional
//	@Override
//	public void remover(RestauranteEntity restaurante) {
//		restaurante = porId(restaurante.getId());
//		manager.remove(restaurante);
//	}

}
