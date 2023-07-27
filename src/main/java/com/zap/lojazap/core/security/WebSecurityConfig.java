package com.zap.lojazap.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("thiago")
			.password(passwordEncoder().encode("123"))
			.roles("ADMIN")
		.and()
			.withUser("joao")
			.password(passwordEncoder().encode("123"))
			.roles("ADMIN");
		
	}
	
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
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
