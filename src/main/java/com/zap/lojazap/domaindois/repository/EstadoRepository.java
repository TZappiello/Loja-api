package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.model.EstadoEntity;

public interface EstadoRepository {
	
	List<EstadoEntity> todas();
	
	EstadoEntity porId(Long id);
	
	EstadoEntity adicionar(EstadoEntity estado);
	
	void remover(EstadoEntity estado);

}
