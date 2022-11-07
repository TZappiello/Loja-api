package com.zap.lojazap.domaindois.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;

public interface CozinhaRepository extends JpaRepository<CozinhaEntity, Long> {
	
	/**
	List<CozinhaEntity> todas();
	CozinhaEntity porId(Long id);
	CozinhaEntity adicionar(CozinhaEntity cozinha);
	void remover(Long id);
	 
	 **/
	List<CozinhaEntity> findTodasBynomeContaining(String nome);
	
	Optional<CozinhaEntity> findNomeCompletoByNome(String nome);
}
