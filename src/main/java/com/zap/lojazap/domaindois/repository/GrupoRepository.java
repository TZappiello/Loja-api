package com.zap.lojazap.domaindois.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zap.lojazap.domaindois.entities.GrupoEntity;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoEntity, Long> {

}
