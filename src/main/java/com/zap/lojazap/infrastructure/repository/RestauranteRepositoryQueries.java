package com.zap.lojazap.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface RestauranteRepositoryQueries {

	List<RestauranteEntity> buscar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}