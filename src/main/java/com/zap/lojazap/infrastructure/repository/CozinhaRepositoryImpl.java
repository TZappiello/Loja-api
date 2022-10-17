package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.model.Cozinha;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> todas(){
		
		return manager.createQuery(" from Cozinha", Cozinha.class)
				.getResultList();
	}

	@Override
	public Cozinha porId(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha adicionar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = porId(cozinha.getId());
		manager.remove(cozinha);
	}

}
