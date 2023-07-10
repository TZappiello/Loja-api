package com.zap.lojazap.domaindois.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStoreService {

	InputStream recuperar(String nomeArquivo);
	
	void armazenar(NovaFoto novaFoto);
	
	void remover(String nomeArquivo);
	
	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		
		if(nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}

	default String gerarNomeAquivo(String nomeOriginal) {
		return  UUID.randomUUID()+"_"+ nomeOriginal;
	}
	
	@Builder
	@Getter
	class NovaFoto{
		
		private String nomeArquivo;
		private String contentType;
		private InputStream inputStream;
	}

}
