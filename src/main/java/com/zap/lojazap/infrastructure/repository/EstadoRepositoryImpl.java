package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.zap.lojazap.domaindois.model.EstadoEntity;
import com.zap.lojazap.domaindois.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<EstadoEntity> todas() {
		return manager.createQuery(" from EstadoEntity", EstadoEntity.class).getResultList();
	}

	@Override
	public EstadoEntity porId(Long id) {
		return manager.find(EstadoEntity.class, id);
	}

	@Override
	public EstadoEntity adicionar(EstadoEntity estado) {
		return manager.merge(estado);
	}

	@Override
	public void remover(EstadoEntity estado) {
		estado = porId(estado.getId());
		manager.remove(estado);
	}

}
