package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.entities.PermissaoEntity;

public interface PermissaoRepository {

	List<PermissaoEntity> todas();
	PermissaoEntity porId(Long id);
	PermissaoEntity adicionar(PermissaoEntity permissao);
	void remover(PermissaoEntity permissao);
	
}
