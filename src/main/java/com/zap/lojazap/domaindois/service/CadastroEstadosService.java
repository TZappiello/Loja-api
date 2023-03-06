package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.repository.EstadoRepository;

@Service
public class CadastroEstadosService {
	
	private static final String MSG_ESTADO_EM_USO
	= "Estado de código %d não pode ser removida, pois está em uso";
	
	private static final String MSG_ESTADO_NAO_ENCONTRADA 
	= "Não existe um cadastro de Estado com código %d";

	@Autowired
	private EstadoRepository estadoRepository;

	public EstadoEntity adicionar(EstadoEntity estados) {
		return estadoRepository.save(estados);
	}

	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADA, id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}

	public EstadoEntity buscarSeTiver(Long id) {
		
		return estadoRepository.findById(id)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADA, id)));
	}

}
