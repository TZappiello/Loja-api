package com.zap.lojazap.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zap.lojazap.api.assember.FotoProdutoModelAssembler;
import com.zap.lojazap.api.dto.FotoProdutoDTO;
import com.zap.lojazap.api.input.FotoProdutoInput;
import com.zap.lojazap.domaindois.entities.FotoProdutoEntity;
import com.zap.lojazap.domaindois.entities.ProdutoEntity;
import com.zap.lojazap.domaindois.exception.EntidadeNaoEncontradaException;
import com.zap.lojazap.domaindois.service.CadastroProdutosService;
import com.zap.lojazap.domaindois.service.CatalogoFotoProdutoService;
import com.zap.lojazap.domaindois.service.FotoStoreService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;

	@Autowired
	private CadastroProdutosService produtosService;

	@Autowired
	private FotoProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private FotoStoreService fotoStore;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {

		ProdutoEntity produto = produtosService.buscarSeTiver(restauranteId, produtoId);

		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProdutoEntity foto = new FotoProdutoEntity();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoProdutoEntity fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());

		return produtoModelAssembler.toDTO(fotoSalva);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProdutoEntity fotoProduto = catalogoFotoProdutoService.buscarSeTiver(restauranteId, produtoId);

//			PODERIA USAR DESSA FORMA TBM!
//			ProdutoEntity produto = produtosService.buscarSeTiver(restauranteId, produtoId);
//			FotoProdutoEntity foto = new FotoProdutoEntity();
//			foto.setProduto(produto);
//			foto.setDescricao(produto.getDescricao());
//			foto.setContentType(fotoBanco.get().getContentType());
//			foto.setTamanho(fotoBanco.get().getTamanho());
//			foto.setNomeArquivo(fotoBanco.get().getNomeArquivo());

		return produtoModelAssembler.toDTO(fotoProduto);
	}

	@GetMapping
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader)
					throws HttpMediaTypeNotAcceptableException {
		
		try {
			FotoProdutoEntity fotoProduto = catalogoFotoProdutoService.buscarSeTiver(restauranteId, produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			var inputStream = fotoStore.recuperar(fotoProduto.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(inputStream));
	
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		catalogoFotoProdutoService.excluir(restauranteId, produtoId);
	}

	private void verificarCompatibidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
}

/*
 * var nomeArquivo = UUID.randomUUID().toString() +"_"+
 * fotoProdutoInput.getArquivo().getOriginalFilename();
 * 
 * var arquivoFoto = Path.of("C:/Users/Zappi/Documents/catalogo", nomeArquivo);
 * 
 * try { fotoProdutoInput.getArquivo().transferTo(arquivoFoto); } catch
 * (IOException e) { throw new RuntimeException(e); }
 */ // modelo de upload de imagens