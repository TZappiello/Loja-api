package com.zap.lojazap.domaindois.repository;

import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;

public interface ProdutoRepositoryQueries {

	FotoProdutoEntity save(FotoProdutoEntity foto);
	
	void delete(FotoProdutoEntity foto);
}
