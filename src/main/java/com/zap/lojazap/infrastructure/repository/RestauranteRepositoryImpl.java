package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.model.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<RestauranteEntity> todas() {

		return manager.createQuery(" from RestauranteEntity ", RestauranteEntity.class).getResultList();
	}

	@Override
	public RestauranteEntity porId(Long id) {
		return manager.find(RestauranteEntity.class, id);
	}

	@Transactional
	@Override
	public RestauranteEntity adicionar(RestauranteEntity restaurante) {
		return manager.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(RestauranteEntity restaurante) {
		restaurante = porId(restaurante.getId());
		manager.remove(restaurante);
	}

}
