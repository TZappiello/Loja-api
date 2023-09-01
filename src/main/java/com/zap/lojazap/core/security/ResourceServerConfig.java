package com.zap.lojazap.core.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		        http
		        	.formLogin()
		        	.and()
		        	.authorizeRequests()
		        		.antMatchers("/oauth").authenticated()
		        	.and()
					.csrf().disable()
			      .cors(withDefaults())
			      .oauth2ResourceServer(server -> server.jwt()
					.jwtAuthenticationConverter(jwtAuthenticationConverter()));
	}
	
//    @Bean
//    SecurityFilterChain filterChainSecurity(HttpSecurity http) throws Exception {
//        http
////                .authorizeHttpRequests()
////                .anyRequest().authenticated()
////                .and()
//        		.csrf().disable()
//                .cors(withDefaults())
//                .oauth2ResourceServer(server -> server.jwt()
//        		.jwtAuthenticationConverter(jwtAuthenticationConverter()));
//        return http.build();
//		
//	}

	@SuppressWarnings("unused")
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
    	var jwtAuthenticationConverter = new JwtAuthenticationConverter();
    	jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
    		var authorities = jwt.getClaimAsStringList("authorities");
    		
    		if(authorities == null) {
    			authorities = Collections.emptyList();
    		}
    		
    		var scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    		Collection<GrantedAuthority> grantedAuthorities = scopesAuthoritiesConverter.convert(jwt);
    		
    		grantedAuthorities.addAll(authorities.stream()
    				.map(SimpleGrantedAuthority::new)
    				.collect(Collectors.toList()));
    		
    		return grantedAuthorities;
    		
    	});
    	
    	return jwtAuthenticationConverter;
    }
    
    
//    @Bean
//    JwtDecoder jwtDecoder() {
//		var secretKey = new SecretKeySpec("uyrkfxzfuyflewyffuydfsyufluflugçiurehoihbcsjl".getBytes(), "HmacSHA256");
//    	
//    	return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
	
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
    	return super.authenticationManager();
    }
    

}

