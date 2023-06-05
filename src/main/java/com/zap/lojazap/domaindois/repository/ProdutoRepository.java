package com.zap.lojazap.domaindois.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	@Transactional(readOnly = true)
	@Query("FROM ProdutoEntity produto "
			+ " WHERE restaurante.id =:restauranteId "
			+ " AND id =:produtoId ")
	Optional<ProdutoEntity> findById(
			@Param("restauranteId") Long restauranteId, 
			@Param("produtoId") Long produtoId);
	
	List<ProdutoEntity> findByRestaurante(RestauranteEntity restaurante);
}
