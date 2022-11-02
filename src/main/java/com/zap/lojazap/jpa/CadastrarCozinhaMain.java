package com.zap.lojazap.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.entities.CozinhaEntity;
import com.zap.lojazap.domaindois.repository.CozinhaRepository;

public class CadastrarCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
			CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
			
					

			CozinhaEntity cozinha1 = new CozinhaEntity();
			cozinha1.setNome("Portuguesa");
			
			CozinhaEntity cozinha2 = new CozinhaEntity();
			cozinha2.setNome("Japonesa");
			
			CozinhaEntity cozinha3 = new CozinhaEntity();
			cozinha3.setNome("Cozinha da Lu");
			
			cozinha1 = cozinhaRepository.save(cozinha1);
			cozinha2 = cozinhaRepository.save(cozinha2);
			cozinha3 = cozinhaRepository.save(cozinha3);
			
		
			System.err.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
			System.err.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
			System.err.printf("%d - %s\n", cozinha3.getId(), cozinha3.getNome());
	}
}




