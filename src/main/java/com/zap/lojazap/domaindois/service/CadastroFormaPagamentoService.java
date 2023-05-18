package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.FormaPagamentoEntity;
import com.zap.lojazap.domaindois.exception.CidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.FormaDePagamentoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	private static final String MSG_CIDADE_EM_USO = "Forma de Pagamento de código %d não pode ser removida, pois está em uso";

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamentoEntity cadastrar(FormaPagamentoEntity entity) {
		return formaPagamentoRepository.save(entity);

	}

	@Transactional
	public void remover(Long id) {
		try {
			formaPagamentoRepository.deleteById(id);
			formaPagamentoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new FormaDePagamentoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id));
		}
	}

	public FormaPagamentoEntity buscarSeTiver(Long id) {
		return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaDePagamentoNaoEncontradoException(id));
	}

}
