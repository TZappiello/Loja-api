//package com.zap.lojazap.model.service.impl;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.ExampleMatcher;
//import org.springframework.data.domain.ExampleMatcher.StringMatcher;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.zap.lojazap.model.entity.Lancamento;
//import com.zap.lojazap.model.enums.StatusLancamento;
//import com.zap.lojazap.model.enums.TipoLancamento;
//import com.zap.lojazap.model.exception.RegraDeNegocioException;
//import com.zap.lojazap.model.repository.LancamentoRepository;
//import com.zap.lojazap.model.service.LancamentoService;
//
//@Service
//public class LancamentoServiceImpl implements LancamentoService {
//
//	@Autowired
//	private LancamentoRepository lancamentoRepository;
//	
//	@Override
//	@Transactional
//	public Lancamento salvar(Lancamento lancamento) {
//		validarLancamento(lancamento);
//		return lancamentoRepository.save(lancamento);
//	}
//
//	@Override
//	@Transactional
//	public Lancamento atualizar(Lancamento lancamento) {
//		Objects.requireNonNull(lancamento.getId());
//		validarLancamento(lancamento);
//		//lancamento.setStatus(StatusLancamento.PENDENTE);
//		return lancamentoRepository.save(lancamento);
//	}
//
//	@Override
//	@Transactional
//	public void deletar(Lancamento lancamento) {
//		Objects.requireNonNull(lancamento);
//		lancamentoRepository.delete(lancamento);
//	}
//
//	@Override
//	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
//		Example<Lancamento> example = Example.of(lancamentoFiltro,
//				ExampleMatcher.matching()
//				.withIgnoreCase()
//				.withStringMatcher(StringMatcher.CONTAINING));
//		return lancamentoRepository.findAll(example);
//	}
//
//	@Override
//	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
//		lancamento.setStatus(status);
//		atualizar(lancamento);
//		
//	}
//
//	@Override
//	public void validarLancamento(Lancamento lancamento) {
//		
//		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
//			throw new RegraDeNegocioException("Informe uma Descrição válida.");
//		}
//		
//		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
//			throw new RegraDeNegocioException("Informe um Mês váliddo.");
//		}
//		
//		if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
//			throw new RegraDeNegocioException("Informe um Ano válido.");
//		}
//		
//		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
//			throw new RegraDeNegocioException("Informe um Usuário.");
//		}
//		
//		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) <1) {
//			throw new RegraDeNegocioException("Informe um Valor válido.");
//		}
//		
//		if(lancamento.getTipo() == null) {
//			throw new RegraDeNegocioException("Informe um Tipo de Lançamento.");
//		}
//	}
//
//	@Override
//	public Optional<Lancamento> obterPorId(Long id) {
//		return lancamentoRepository.findById(id);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public BigDecimal obterSaldoPorUsuario(Long id) {
//			BigDecimal receitas = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.RECEITA);
//			BigDecimal despesas = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.DESPESA);
//			
//			if(receitas == null) {
//				receitas = BigDecimal.ZERO;
//			}
//	
//			if(despesas == null) {
//				despesas = BigDecimal.ZERO;
//			}
//			
//		return receitas.subtract(despesas);
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
