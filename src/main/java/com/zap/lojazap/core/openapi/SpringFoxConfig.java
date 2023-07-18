package com.zap.lojazap.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean 
    Docket apiDockt() {		//DOCUMENTAÇÃO DO SWAGGER
        return new Docket(DocumentationType.OAS_30)
                .select()
                  .apis(RequestHandlerSelectors.basePackage("com.zap.lojazap.api"))
                  .build()
                 .apiInfo(apiInfo());
          }
    
    public ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("LojaZap API")
    			.description("API aberta para clientes e restaurantes")
    			.version("1")
    			.contact(new Contact("Zap_API", "https://github.com/TZappiello", "contato@zappi.com.br"))
    			.build();
    }
    
	// 	 http://localhost:8080/swagger-ui/#/       	 LINK PARA ACESSA O SWAGGER
    @Override  	// NÃO PRECISA USAR ESSE MÉTODO 	NEM DAS DEPENDENCIAS
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("swagger-ui.html")
    		.addResourceLocations("classpath:/META-INF/resources/");
    }
}
