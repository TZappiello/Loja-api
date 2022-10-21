//package com.zap.lojazap.model.service;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import com.zap.lojazap.model.entity.Lancamento;
//import com.zap.lojazap.model.enums.StatusLancamento;
//
//public interface LancamentoService {
//
//	Lancamento salvar(Lancamento lancamento);
//	
//	Lancamento atualizar(Lancamento lancamento);
//	
//	void deletar(Lancamento lancamento);
//	
//	List<Lancamento> buscar(Lancamento lancamentoFiltro);
//	
//	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
//	
//	void validarLancamento(Lancamento lancamento);
//	
//	Optional<Lancamento> obterPorId(Long id);
//	
//	BigDecimal obterSaldoPorUsuario(Long id);
//	
//}
