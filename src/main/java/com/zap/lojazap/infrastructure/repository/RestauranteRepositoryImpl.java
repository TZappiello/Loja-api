package com.zap.lojazap.infrastructure.repository;

import static com.zap.lojazap.infrastructure.repository.spec.RestauranteSpec.comFreteGratis;
import static com.zap.lojazap.infrastructure.repository.spec.RestauranteSpec.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;
import com.zap.lojazap.domaindois.repository.RestauranteRepositoryQueries;


@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;

	@Override
	public List<RestauranteEntity> buscar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

//		var builder = manager.getCriteriaBuilder();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

//		CriteriaQuery<RestauranteEntity> criteria = builder.createQuery(RestauranteEntity.class);
//		Root<RestauranteEntity> root = criteria.from(RestauranteEntity.class);
		var criteria = builder.createQuery(RestauranteEntity.class);
		var root = criteria.from(RestauranteEntity.class);

		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}

		if(taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}

		if(taxaFreteInicial != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		
		
		criteria.where(predicates.toArray(new Predicate[0]));

		
//		var query = manager.createQuery(criteria);
		TypedQuery<RestauranteEntity> query = manager.createQuery(criteria);
		return query.getResultList();

	}

	@Override
	public List<RestauranteEntity> freteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
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
