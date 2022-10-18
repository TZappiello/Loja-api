package com.zap.lojazap.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.zap.lojazap.domaindois.model.PermissaoEntity;
import com.zap.lojazap.domaindois.repository.PermissaoRepository;

public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PermissaoEntity> todas() {
		return manager.createQuery("from PermissaoEntity ", PermissaoEntity.class).getResultList();
	}

	@Override
	public PermissaoEntity porId(Long id) {
		return manager.find(PermissaoEntity.class, id);
	}

	@Override
	public PermissaoEntity adicionar(PermissaoEntity permissao) {
		return manager.merge(permissao);
	}

	@Override
	public void remover(PermissaoEntity permissao) {
		permissao = porId(permissao.getId());
		manager.remove(permissao);
	}

}
