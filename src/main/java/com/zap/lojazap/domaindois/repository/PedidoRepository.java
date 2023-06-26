package com.zap.lojazap.domaindois.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.zap.lojazap.domaindois.entities.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>,
	 JpaSpecificationExecutor<PedidoEntity>{

	Optional<PedidoEntity> findByCodigo(String codigo);
	
	@Query(" FROM PedidoEntity p "
		 + " JOIN FETCH p.cliente "
		 + " JOIN FETCH p.restaurante r "
		 + " JOIN FETCH r.cozinha ")
	List<PedidoEntity> findAll();
}
