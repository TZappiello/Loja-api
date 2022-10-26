package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.model.CidadeEntity;
import com.zap.lojazap.domaindois.model.EstadoEntity;
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
	
}
