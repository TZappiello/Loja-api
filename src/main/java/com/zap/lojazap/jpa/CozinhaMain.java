package com.zap.lojazap.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.model.Cozinha;

public class CozinhaMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
			CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
			
			List<Cozinha> cozinhas = cadastroCozinha.listar();
			
			for(Cozinha cozinha : cozinhas) {
				System.out.println(cozinha.getNome());
			}
	}
}
