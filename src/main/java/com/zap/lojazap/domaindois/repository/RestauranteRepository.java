package com.zap.lojazap.domaindois.repository;

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

}
