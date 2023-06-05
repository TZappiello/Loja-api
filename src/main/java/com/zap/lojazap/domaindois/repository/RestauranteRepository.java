package com.zap.lojazap.domaindois.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface RestauranteRepository 
		extends JpaRepository<RestauranteEntity, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<RestauranteEntity> {

//	List<RestauranteEntity> todas();
//	RestauranteEntity porId(Long id);
//	RestauranteEntity adicionar(RestauranteEntity cozinha);
//	void remover(RestauranteEntity cozinha);
	
	//@Query("FROM RestauranteEntity r JOIN r.cozinha LEFT JOIN FETCH r. formasPagamento "
	@Query("FROM RestauranteEntity r JOIN FETCH r.cozinha")
	List<RestauranteEntity> findAll();
	
	Optional<RestauranteEntity> findNomeCompletoByNome(String name);
	Optional<RestauranteEntity> findTaxaByTaxaFrete(BigDecimal taxa);
	

//	@Query("FROM RestauranteEntity WHERE nome LIKE %:nome% AND cozinha.id =:id")
	List<RestauranteEntity> buscarPorNome(String nome, @Param("id") Long cozinha);

	List<RestauranteEntity> queryBytaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	List<RestauranteEntity> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

	boolean existsByNome(String nome);

	int countByCozinhaId(Long cozinha);
	
	@Transactional(readOnly = true)
	@Query("FROM RestauranteEntity restaurante "
			+ " JOIN ProdutoEntity produtos ON restaurante.id = produtos.id "
			+ " WHERE :restauranteId IS NULL OR restaurante.id =: restauranteId "
			+ " AND :produtoId IS NULL OR produtos.id =: produtoId ")
	Optional<RestauranteEntity> restauranteProduto(
			@Param("restauranteId") Long restauranteId, 
			@Param("produtoId") Long produtoId);

}
