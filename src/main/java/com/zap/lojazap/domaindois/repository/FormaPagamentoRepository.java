package com.zap.lojazap.domaindois.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoEntity, Long> {

	@Query("SELECT MAX(dataAtualizacao) FROM FormaPagamentoEntity")
	OffsetDateTime getDataUltimaatualizacao();
//	List<FormaPagamentoEntity> todas();
//	FormaPagamentoEntity porId(Long id);
//	FormaPagamentoEntity adicionar(FormaPagamentoEntity pagamento);
//	void remover (FormaPagamentoEntity pagamento);
}
