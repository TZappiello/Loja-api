package com.zap.lojazap.core.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .cors(withDefaults())
                .oauth2ResourceServer(server -> server.jwt());
        return http.build();
		
	}

//    @Bean
//    JwtDecoder jwtDecoder() {
//		var secretKey = new SecretKeySpec("uyrkfxzfuyflewyffuydfsyufluflugçiurehoihbcsjl".getBytes(), "HmacSHA256");
//    	
//    	return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}

}

