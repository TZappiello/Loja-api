package com.zap.lojazap.domaindois.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.exception.CozinhaNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO
		= "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public CozinhaEntity adicionar(CozinhaEntity cozinha) {
		Optional<CozinhaEntity> contem = cozinhaRepository.findNomeCompletoByNome(cozinha.getNome());
		
//		if(contem.isPresent()) {
//			System.err.println("AQUI TEM ESSA COZINHA!!!");
//			throw new EntidadeEmUsoException(
//					String.format(MSG_COZINHA_EM_USO, cozinha.getId()));
//		}
		
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id));
		}
	}
	
	public  CozinhaEntity buscarSeTiver(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}
}
