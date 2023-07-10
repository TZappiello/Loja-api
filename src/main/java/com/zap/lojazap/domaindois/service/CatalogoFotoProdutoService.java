package com.zap.lojazap.domaindois.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;
import com.zap.lojazap.domaindois.exception.FotoProdutoNaoEncontradoException;
import com.zap.lojazap.domaindois.repository.ProdutoRepository;
import com.zap.lojazap.domaindois.service.FotoStoreService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStoreService fotoStore;

	@Transactional
	public FotoProdutoEntity salvar(FotoProdutoEntity foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoAquivo = fotoStore.gerarNomeAquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProdutoEntity> fotoExistente = produtoRepository.findByFotoExistente(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}

		foto.setNomeArquivo(nomeNovoAquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();			 // descarrega tudo que esta na fila 		
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.inputStream(dadosArquivo)
				.build();
		
		fotoStore.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	public FotoProdutoEntity buscarSeTiver(Long restauranteId, Long produtoId) {
		return produtoRepository.findByFotoExistente(restauranteId, produtoId).
				orElseThrow(()-> new FotoProdutoNaoEncontradoException(restauranteId, produtoId));
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		FotoProdutoEntity fotoProduto = buscarSeTiver(restauranteId, produtoId);
		produtoRepository.delete(fotoProduto);
		produtoRepository.flush();	
		
		fotoStore.remover(fotoProduto.getNomeArquivo());
	}
}
