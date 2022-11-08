package com.zap.lojazap.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<RestauranteEntity> buscar(String nome, BigDecimal taxaFreteInicial,
					BigDecimal taxaFreteFinal) {
		
		var jpql = "FROM RestauranteEntity WHERE nome LIKE :nome "
					+ "AND taxaFrente BETWEEN :taxaFreteInicial AND :taxaFreteFinal";

		return manager.createQuery(jpql, RestauranteEntity.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("taxaFreteInicial", taxaFreteInicial)
				.setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
	}

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
