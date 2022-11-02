package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.entities.RestauranteEntity;

public interface RestauranteRepository {

	List<RestauranteEntity> todas();

	RestauranteEntity porId(Long id);

	RestauranteEntity adicionar(RestauranteEntity cozinha);

	void remover(RestauranteEntity cozinha);

}
