package com.zap.lojazap.infrastructure.repository.storege;

import java.io.InputStream;

import com.zap.lojazap.domaindois.service.FotoStoreService;

//@Service
public class S3FotosStogareService implements FotoStoreService {

	@Override
	public InputStream recuperar(String nomeArquivo) {
		return null;
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
		
	}

	@Override
	public void remover(String nomeArquivo) {
		
	}

}
