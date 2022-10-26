package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.zap.lojazap.domaindois.model.CidadeEntity;
import com.zap.lojazap.domaindois.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<CidadeEntity> todas() {
		return manager.createQuery(" from CidadeEntity", CidadeEntity.class).getResultList();
	}

	@Override
	public CidadeEntity porId(Long id) {
		return manager.find(CidadeEntity.class, id);
	}

	@Transactional
	@Override
	public CidadeEntity adicionar(CidadeEntity cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(CidadeEntity cidade) {
		cidade = porId(cidade.getId());
		manager.remove(cidade);
	}

}
