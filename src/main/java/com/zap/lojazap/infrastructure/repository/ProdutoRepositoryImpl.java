package com.zap.lojazap.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;
import com.zap.lojazap.domaindois.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoProdutoEntity save(FotoProdutoEntity foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void delete(FotoProdutoEntity foto) {
		manager.remove(foto);
	}

}
