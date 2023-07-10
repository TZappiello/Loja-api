package com.zap.lojazap.infrastructure.repository.storege;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.zap.lojazap.core.storage.StorageProperties;
import com.zap.lojazap.domaindois.service.FotoStoreService;

//@Service
public class S3FotosStogareService implements FotoStoreService {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public InputStream recuperar(String nomeArquivo) {
		return null;
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
			
			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContentType());
			
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(),
					caminhoArquivo,
					novaFoto.getInputStream(),
					objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);	
			
			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar o arquivo para Amazon S3.", e);
		}
		
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}
	
	@Override
	public void remover(String nomeArquivo) {
		
	}

}
