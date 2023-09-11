package com.zap.lojazap;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zap.lojazap.core.io.Base64ProtocolResolver;

@EnableWebMvc		//ADICIONAR PARA ACERTAR O ERRO DE INICIALIZAÇÃO DO SWAGGER
@SpringBootApplication
public class LojaApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		var app = new SpringApplication(LojaApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
		
//	SpringApplication.run(LojaApiApplication.class, args);
	}

}
