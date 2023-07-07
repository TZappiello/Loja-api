package com.zap.lojazap.domaindois.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> , ProdutoRepositoryQueries {

	@Transactional(readOnly = true)
	@Query("FROM ProdutoEntity produto "
			+ " WHERE restaurante.id =:restauranteId "
			+ " AND id =:produtoId ")
	Optional<ProdutoEntity> findById(
			@Param("restauranteId") Long restauranteId, 
			@Param("produtoId") Long produtoId);
	
	List<ProdutoEntity> findByRestaurante(RestauranteEntity restaurante);
	
	@Query("FROM ProdutoEntity p "
			+ " WHERE p.ativo = true "
			+ " AND p.restaurante =:restaurante")
	List<ProdutoEntity> findByAtivo(RestauranteEntity restaurante);
	
	@Query("SELECT f FROM FotoProdutoEntity f "
			+ " JOIN f.produto p "
			+ " WHERE f.produto.id = :produtoId "
			+ " AND p.restaurante.id = :restauranteId ")
	Optional<FotoProdutoEntity> findByFotoExistente(Long restauranteId, Long produtoId);

}
