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

@Service
public class CadastroCidadesService {
	
	private static final String MSG_CIDADE_EM_USO
	= "Cozinha de código %d não pode ser removida, pois está em uso";
	
	private static final String MSG_CIDADE_NAO_ENCONTRADA 
	= "Não existe um cadastro da Cidade com código %d";

	@Autowired
	private CidadeRepository cidadeRepository;
		
	@Autowired
	private CadastroEstadosService cadastroEstadosService;
	
	public CidadeEntity cadastrar(CidadeEntity cidade) {
		Long estadoId = cidade.getEstado().getId();
		EstadoEntity estado = cadastroEstadosService.buscarSeTiver(estadoId);
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
		
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id));
		}
	}

	public CidadeEntity buscarSeTiver(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
	}
	
}
