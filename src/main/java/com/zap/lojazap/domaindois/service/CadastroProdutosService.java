package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.api.DTO.ProdutoDTO;
import com.zap.lojazap.domaindois.entities.CidadeEntity;
import com.zap.lojazap.domaindois.entities.EstadoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.exception.CidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.exception.EntidadeEmUsoException;
import com.zap.lojazap.domaindois.exception.NegocioException;
import com.zap.lojazap.domaindois.exception.ProdutoNaoEncontadoException;
import com.zap.lojazap.domaindois.repository.ProdutoRepository;

@Service
public class CadastroProdutosService {
	
	private static final String MSG_CIDADE_EM_USO
	= "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private ProdutoRepository produtoRepository;
		
	@Autowired
	private CadastroEstadosService cadastroEstadosService;
	
//	@Transactional
//	public CidadeEntity cadastrar(CidadeEntity cidade) {
//		Long estadoId = cidade.getEstado().getId();
//		EstadoEntity estado = cadastroEstadosService.buscarSeTiver(estadoId);
//		
//		cidade.setEstado(estado);
//		
//		return produtoRepository.save(cidade);
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


	public ProdutoEntity buscarSeTiver(Long id) {
		ProdutoEntity produto = produtoRepository.findById(id)
				.orElseThrow(()-> new ProdutoNaoEncontadoException(id));
		return produto;
	}
}

