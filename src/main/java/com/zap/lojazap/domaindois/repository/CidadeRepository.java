package com.zap.lojazap.domaindois.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.domaindois.entities.CidadeEntity;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {
	
//	List<CidadeEntity> todas();
//	
//	CidadeEntity porId(Long id);
//	
//	CidadeEntity adicionar(CidadeEntity cidade);
//	
//	void remover(Long id);

}
