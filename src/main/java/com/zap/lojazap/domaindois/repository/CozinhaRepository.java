package com.zap.lojazap.domaindois.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;

public interface CozinhaRepository extends JpaRepository<CozinhaEntity, Long> {
	
	/**
	List<CozinhaEntity> todas();
	List<CozinhaEntity> listarPorNome(String nome);
	CozinhaEntity porId(Long id);
	CozinhaEntity adicionar(CozinhaEntity cozinha);
	void remover(Long id);
	 
	 **/

}
