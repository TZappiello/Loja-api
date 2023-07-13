package com.zap.lojazap.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("*");
//		.allowedOrigins("*") 	ORIGEN DOS METODOS
//		.maxAge(30);			QUANTIDADE DE TEMPO PARA O CACHE NA CHAMADA OPTIONS
		
	}
	
}
