package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.exception.FormaDePagamentoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {
	
	private static final String MSG_CIDADE_EM_USO
	= "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
//	@Transactional
//	public CidadeEntity cadastrar(CidadeEntity cidade) {
//		Long estadoId = cidade.getEstado().getId();
//		EstadoEntity estado = cadastroEstadosService.buscarSeTiver(estadoId);
//		
//		cidade.setEstado(estado);
//		
//		return cidadeRepository.save(cidade);
//		
//	}
//	
//	@Transactional
//	public void remover(Long id) {
//		try {
//			cidadeRepository.deleteById(id);
//			cidadeRepository.flush();
//			
//		} catch (EmptyResultDataAccessException e) {
//			throw new CidadeNaoEncontradaException(id);
//
//		} catch (DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(
//					String.format(MSG_CIDADE_EM_USO, id));
//		}
//	}

	public FormaPagamentoEntity buscarSeTiver(Long id) {
		return formaPagamentoRepository.findById(id)
				.orElseThrow(()-> new FormaDePagamentoNaoEncontradoException(id));
	}
	
}
