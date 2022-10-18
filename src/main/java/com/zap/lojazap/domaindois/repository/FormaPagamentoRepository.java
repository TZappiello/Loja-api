package com.zap.lojazap.domaindois.repository;

import java.util.List;

import com.zap.lojazap.domaindois.model.FormaPagamentoEntity;

public interface FormaPagamentoRepository {

	List<FormaPagamentoEntity> todas();
	FormaPagamentoEntity porId(Long id);
	FormaPagamentoEntity adicionar(FormaPagamentoEntity pagamento);
	void remover (FormaPagamentoEntity pagamento);
	
}
