package com.zap.lojazap.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zap.lojazap.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
