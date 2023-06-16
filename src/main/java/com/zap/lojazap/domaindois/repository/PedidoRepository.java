package com.zap.lojazap.domaindois.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap.lojazap.domaindois.entities.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

	@Query(" FROM PedidoEntity p "
		 + " JOIN FETCH p.cliente "
		 + " JOIN FETCH p.restaurante r "
		 + " JOIN FETCH r.cozinha ")
	List<PedidoEntity> findAll();
}
