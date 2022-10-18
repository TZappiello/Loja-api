package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.zap.lojazap.domaindois.model.CidadeEntity;
import com.zap.lojazap.domaindois.repository.CidadeRepository;

public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<CidadeEntity> todas() {
		return manager.createQuery(" from cidades", CidadeEntity.class).getResultList();
	}

	@Override
	public CidadeEntity porId(Long id) {
		return manager.find(CidadeEntity.class, id);
	}

	@Override
	public CidadeEntity adicionar(CidadeEntity cidade) {
		return manager.merge(cidade);
	}

	@Override
	public void remover(CidadeEntity cidade) {
		cidade = porId(cidade.getId());
		manager.remove(cidade);
	}

}
