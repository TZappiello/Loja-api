package com.zap.lojazap.domaindois.service;

import java.util.Optional;

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
	public FotoProdutoEntity salvar(FotoProdutoEntity foto) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();

		Optional<FotoProdutoEntity> fotoExistente = produtoRepository.findByFotoExistente(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}

		return produtoRepository.save(foto);
	}
}
