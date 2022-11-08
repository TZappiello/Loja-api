package com.zap.lojazap.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.zap.lojazap.LojaApiApplication;
import com.zap.lojazap.domaindois.entities.RestauranteEntity;
import com.zap.lojazap.domaindois.repository.RestauranteRepository;

public class CozinhaRestauranteMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(LojaApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
			
			List<RestauranteEntity> restaurantes = restauranteRepository.findAll();
			
			for(RestauranteEntity restaurante : restaurantes) {
				System.err.printf("%s - %f - %s\n",restaurante.getNome(),
						restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
			}
	}
}
