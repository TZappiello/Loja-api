package com.zap.lojazap.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

public class RemoverCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		CozinhaEntity cozinha = new CozinhaEntity();
		cozinha.setId(2L);
		
		CozinhaEntity cozinha2 = new CozinhaEntity();
		cozinha2.setId(1L);
		
		cozinhaRepository.delete(null);
		cozinhaRepository.delete(null);

	}
}
