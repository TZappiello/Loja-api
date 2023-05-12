package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.EstadoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.EstadoRepository;

@Service
public class CadastroEstadosService {
	
	private static final String MSG_ESTADO_EM_USO
	= "Estado de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public EstadoEntity adicionar(EstadoEntity estados) {
		return estadoRepository.save(estados);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
			estadoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}

	public EstadoEntity buscarSeTiver(Long id) {
		
		EstadoEntity estado = estadoRepository.findById(id)
				.orElseThrow(()-> new EstadoNaoEncontradoException(id));
		
		return estado;
	}

}
