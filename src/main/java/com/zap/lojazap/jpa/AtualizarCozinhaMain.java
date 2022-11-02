package com.zap.lojazap.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

public class AtualizarCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
			CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
			
					

			CozinhaEntity cozinha = new CozinhaEntity();
			cozinha.setId(1L);
			cozinha.setNome("Portuguesa");
			
			CozinhaEntity cozinha2 = new CozinhaEntity();
			cozinha2.setId(2L);
			cozinha2.setNome("Tailandesa");
			
			CozinhaEntity cozinha3 =new CozinhaEntity();
			cozinha3.setId(3L);
			cozinha3.setNome("Mexicana");

			cozinha = cozinhaRepository.save(cozinha);
			cozinha2 = cozinhaRepository.save(cozinha2);
			cozinha3 = cozinhaRepository.save(cozinha3);

			System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome());
			System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
			System.out.printf("%d - %s\n", cozinha3.getId(), cozinha3.getNome());

	}
}




