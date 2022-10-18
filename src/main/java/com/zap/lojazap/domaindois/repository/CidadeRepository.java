package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.model.CidadeEntity;

public interface CidadeRepository {
	
	List<CidadeEntity> todas();
	
	CidadeEntity porId(Long id);
	
	CidadeEntity adicionar(CidadeEntity cidade);
	
	void remover(CidadeEntity cidade);

}
