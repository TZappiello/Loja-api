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
	
	private static final String MSG_VIDADE_NAO_ENCONTRADA 
	= "Não existe um cadastro da Cidade com código %d";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public CidadeEntity cadastrar(CidadeEntity cidade) {
		Long estadoId = cidade.getEstado().getId();
		EstadoEntity estado = estadoRepository.findById(estadoId)
				.orElseThrow(()->
				new EntidadeNaoEncontradaException(
						String.format("Não existe estado cadastra com código %d ", estadoId)));
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
		
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
	}

	public CidadeEntity buscarSeTiver(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format(MSG_VIDADE_NAO_ENCONTRADA, id)));
	}
	
}
