package com.zap.lojazap.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.model.Cozinha;

public class RemoverCozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);


		Cozinha cozinha = new Cozinha();
		cozinha.setId(2L);
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setId(1L);
		
		cadastroCozinha.remover(cozinha);
		cadastroCozinha.remover(cozinha2);

	}
}
