package com.zap.lojazap.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zap.lojazap.api.assember.FotoProdutoModelAssembler;
import com.zap.lojazap.api.dto.FotoProdutoDTO;
import com.zap.lojazap.api.input.FotoProdutoInput;
import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.service.CadastroProdutosService;
import com.zap.lojazap.domaindois.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired
	private CadastroProdutosService produtosService; 
	
	@Autowired
	private FotoProdutoModelAssembler produtoModelAssembler;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) {
	
		ProdutoEntity produto = produtosService.buscarSeTiver(restauranteId, produtoId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		FotoProdutoEntity foto = new FotoProdutoEntity();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProdutoEntity fotoSalva = catalogoFotoProdutoService.salvar(foto);
		
		return produtoModelAssembler.toDTO(fotoSalva);
	}
}


	/*	var nomeArquivo = UUID.randomUUID().toString() 
				+"_"+ fotoProdutoInput.getArquivo().getOriginalFilename();
		 
		var arquivoFoto = Path.of("C:/Users/Zappi/Documents/catalogo", nomeArquivo);
		
		try {
			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} */   //modelo de upload de imagens