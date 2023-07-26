package com.zap.lojazap.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic()
				.and()
					.authorizeHttpRequests()
						.antMatchers("/cozinhas/**").permitAll()
						.anyRequest().authenticated()
						
						.and()
							.sessionManagement()
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // LIMPA OS COOKIES
								
								.and()
									.csrf().disable(); // DEIXA AS CHAMADAS POSTs PASSAR
		
	}
}
