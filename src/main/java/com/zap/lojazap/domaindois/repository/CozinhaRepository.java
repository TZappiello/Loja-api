package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.model.CozinhaEntity;

public interface CozinhaRepository {
	
	List<CozinhaEntity> todas();
	
	CozinhaEntity porId(Long id);
	
	CozinhaEntity adicionar(CozinhaEntity cozinha);
	
	void remover(CozinhaEntity cozinha);

}
