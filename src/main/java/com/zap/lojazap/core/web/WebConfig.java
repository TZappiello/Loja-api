package com.zap.lojazap.core.web;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//		.allowedMethods("*");
////		.allowedOrigins("*") 	ORIGEN DOS METODOS
////		.maxAge(30);			QUANTIDADE DE TEMPO PARA O CACHE NA CHAMADA OPTIONS
//		
//	}   FOI TIRADO DEPOIS QUE COLOCOU O AUTHORIZATION SERVE NO MESMO PRJ
	
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter(); 	// FAZ A CONFIGURAÇÃO DO ETAG PARA CACHEAR AS REQUISIÇÕES
	}
	
}
