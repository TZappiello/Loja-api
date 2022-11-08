package com.zap.lojazap.domaindois.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.infrastructure.repository.RestauranteRepositoryQueries;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long>, 
RestauranteRepositoryQueries {

//	List<RestauranteEntity> todas();
//	RestauranteEntity porId(Long id);
//	RestauranteEntity adicionar(RestauranteEntity cozinha);
//	void remover(RestauranteEntity cozinha);
	
//	@Query("FROM RestauranteEntity WHERE nome LIKE %:nome% AND cozinha.id =:id")
	List<RestauranteEntity> buscarPorNome(String nome, @Param("id") Long cozinha);
	
	List<RestauranteEntity> queryBytaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	List<RestauranteEntity> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
	
	boolean existsByNome(String nome);
	
	int countByCozinhaId(Long cozinha);
	
}
