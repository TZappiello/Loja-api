package com.zap.lojazap.domaindois.repository;

import java.math.BigDecimal;
import java.util.List;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface RestauranteRepositoryQueries {

	List<RestauranteEntity> buscar(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	List<RestauranteEntity> freteGratis(String nome);
}