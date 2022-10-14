package com.zap.lojazap.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.model.Cozinha;

public class CozinhaMain2 {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
			CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
			
					

			Cozinha cozinha1 = new Cozinha();
			cozinha1.setNome("Portuguesa");
			
			Cozinha cozinha2 = new Cozinha();
			cozinha2.setNome("Japonesa");
			
			Cozinha cozinha3 = new Cozinha();
			cozinha3.setNome("Cozinha da Lu");
			
			cozinha1 = cadastroCozinha.cadastrar(cozinha1);
			cozinha2 = cadastroCozinha.cadastrar(cozinha2);
			cozinha3 = cadastroCozinha.cadastrar(cozinha3);
			
			
			
			System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
			System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
			System.out.printf("%d - %s\n", cozinha3.getId(), cozinha3.getNome());
	}
}




