package com.zap.lojazap.domaindois.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

//	List<RestauranteEntity> todas();
//
//	RestauranteEntity porId(Long id);
//
//	RestauranteEntity adicionar(RestauranteEntity cozinha);
//
//	void remover(RestauranteEntity cozinha);
	
	List<RestauranteEntity> queryBytaxaFrenteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<RestauranteEntity> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	boolean existsByNome(String nome);
	
	int countByCozinhaId(Long cozinha);
}
