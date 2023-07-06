package com.zap.lojazap.domaindois.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;
import com.zap.lojazap.domaindois.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public FotoProdutoEntity salvar (FotoProdutoEntity foto) {
		return produtoRepository.save(foto);
	}
}
