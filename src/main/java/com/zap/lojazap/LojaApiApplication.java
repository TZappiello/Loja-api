package com.zap.lojazap;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc		//ADICIONAR PARA ACERTAR O ERRO DE INICIALIZAÇÃO DO SWAGGER
@SpringBootApplication
public class LojaApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(LojaApiApplication.class, args);
	}

}
