package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;

public interface CozinhaRepository {
	
	List<CozinhaEntity> todas();
	List<CozinhaEntity> listarPorNome(String nome);
	CozinhaEntity porId(Long id);
	CozinhaEntity adicionar(CozinhaEntity cozinha);
	void remover(Long id);

}
