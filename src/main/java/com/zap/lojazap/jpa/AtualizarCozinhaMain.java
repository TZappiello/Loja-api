package com.zap.lojazap.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.model.Cozinha;

public class AtualizarCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
			CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
			
					

			Cozinha cozinha = new Cozinha();
			cozinha.setId(1L);
			cozinha.setNome("Portuguesa");
			
			Cozinha cozinha2 = new Cozinha();
			cozinha2.setId(2L);
			cozinha2.setNome("Tailandesa");
			
			Cozinha cozinha3 =new Cozinha();
			cozinha3.setId(3L);
			cozinha3.setNome("Mexicana");

			cozinha = cadastroCozinha.cadastrar(cozinha);
			cozinha2 = cadastroCozinha.cadastrar(cozinha2);
			cozinha3 = cadastroCozinha.cadastrar(cozinha3);

			System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome());
			System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
			System.out.printf("%d - %s\n", cozinha3.getId(), cozinha3.getNome());

	}
}




