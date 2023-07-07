package com.zap.lojazap.infrastructure.repository.storege;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.zap.lojazap.domaindois.service.FotoStoreService;

//@Service
public class S3FotosStogareService implements FotoStoreService {

	@Autowired
	private AmazonS3 amazonS3;
	
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
