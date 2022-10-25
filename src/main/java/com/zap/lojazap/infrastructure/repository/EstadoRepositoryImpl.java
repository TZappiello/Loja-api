package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
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

	@Transactional
	@Override
	public EstadoEntity adicionar(EstadoEntity estados) {
		return manager.merge(estados);
	}

	@Transactional
	@Override 
	public void remover(EstadoEntity estado) {
		estado = porId(estado.getId());
		
		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(estado);
	}

}
