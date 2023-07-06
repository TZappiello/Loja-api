package com.zap.lojazap.domaindois.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface FotoStoreService {

	void armazenar(NovaFoto novaFoto);
	
	@Builder
	@Getter
	class NovaFoto{
		
		private String nomeAquivo;
		private InputStream inputStream;
	}
}
