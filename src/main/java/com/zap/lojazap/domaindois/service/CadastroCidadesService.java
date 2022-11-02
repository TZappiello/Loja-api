package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.CidadeRepository;
import com.zap.lojazap.domaindois.repository.EstadoRepository;

@Service
public class CadastroCidadesService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public CidadeEntity cadastrar(CidadeEntity cidade) {
		Long estadoId = cidade.getEstado().getId();
		EstadoEntity estado = estadoRepository.porId(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe estado cadastra com código %d ", estadoId));
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.adicionar(cidade);
		
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.remover(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
	}
}
