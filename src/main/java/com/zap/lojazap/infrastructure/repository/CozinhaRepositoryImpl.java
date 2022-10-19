package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.model.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<CozinhaEntity> todas() {
		return manager.createQuery(" from CozinhaEntity", CozinhaEntity.class).getResultList();
	}

	@Override
	public CozinhaEntity porId(Long id) {
		return manager.find(CozinhaEntity.class, id);
	}

	@Transactional
	@Override
	public CozinhaEntity adicionar(CozinhaEntity cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(CozinhaEntity cozinha) {
		cozinha = porId(cozinha.getId());
		manager.remove(cozinha);
	}

}
