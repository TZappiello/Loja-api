package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> todas();
	Cozinha porId(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Cozinha cozinha);

}
