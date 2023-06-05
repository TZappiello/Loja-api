package com.zap.lojazap.domaindois.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.exception.ProdutoNaoEncontadoException;
import com.zap.lojazap.domaindois.repository.ProdutoRepository;

@Service
public class CadastroProdutosService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
		
	@Transactional
	public ProdutoEntity cadastrar(ProdutoEntity produto) {
		return produtoRepository.save(produto);
		
	}

	public ProdutoEntity buscarSeTiver(Long resrauranetId, Long id) {
		ProdutoEntity produto = produtoRepository.findById(resrauranetId, id)
				.orElseThrow(()-> new ProdutoNaoEncontadoException(resrauranetId, id));
		return produto;
	}
}

