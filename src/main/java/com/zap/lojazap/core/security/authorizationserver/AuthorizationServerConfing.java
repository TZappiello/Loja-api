package com.zap.lojazap.core.security.authorizationserver;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfing extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;

	@Autowired
	private DataSource dataSource;
	
//	@Autowired
//	private RedisConnectionFactory connectionFactory;
	
	 @Value("${jwt.secret}")
	 private String secret;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.jdbc(dataSource);
		
//			.inMemory()
//				.withClient("loja-api-web")
//				.secret(passwordEncoder.encode("loja123"))
//				.authorizedGrantTypes("password", "refresh_token")
//				.scopes("ESCRITA", "LEITURA")
//				.accessTokenValiditySeconds(60 * 60 * 6)		//6 horas
//				.refreshTokenValiditySeconds(60 * 24 * 60 * 60) //60 dias
//		
//			.and()
//				.withClient("lojaanalista")
//				.secret(passwordEncoder.encode(""))
//				.authorizedGrantTypes("authorization_code")
//				.scopes("ESCRITA", "LEITURA")
//				.redirectUris("http://127.0.0.1:5500/")
//			//http://localhost:8081/oauth/authorize?response_type=code&client_id=lojaanalista&state=abc&redirect_uri=http://aplicacao-cliente
//			.and()
//				.withClient("faturamento")
//				.secret(passwordEncoder.encode("faturamento123"))
//				.authorizedGrantTypes("client_credentials")
//				.scopes("ESCRITA", "LEITURA")
//				
//			.and()	
//				.withClient("webadmin")
//				.authorizedGrantTypes("implicit")
//				.scopes("ESCRITA", "LEITURA")
//				.redirectUris("http://client-aplication")
//			
//			.and()
//				.withClient("checktoken")
//				.secret(passwordEncoder.encode("check123"));
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
		security.checkTokenAccess("permitAll()")
			.tokenKeyAccess("permitAll()")
			.allowFormAuthenticationForClients();
		
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		var enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(
				Arrays.asList(new JwtCustomClaimsTokenEnhacer(), jwtAccessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.reuseRefreshTokens(false)
			.accessTokenConverter(jwtAccessTokenConverter())
			.tokenEnhancer(enhancerChain)
//			.tokenStore(redisTokenStore())
			.tokenGranter(tokenGranter(endpoints));
	}
	
//	private TokenStore redisTokenStore() {
//		return new RedisTokenStore(connectionFactory);
//	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		
		var keyStorePass = jwtKeyStoreProperties.getPassword();
		var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();
		
		var keyStoreKeyFactory = new KeyStoreKeyFactory(
				jwtKeyStoreProperties.getJksLocation(), keyStorePass.toCharArray());
		var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);
		
		jwtAccessTokenConverter.setKeyPair(keyPair);
//		jwtAccessTokenConverter.setSigningKey("uyrkfxzfuyflewyffuydfsyufluflugçiurehoihbcsjl");
		
		return jwtAccessTokenConverter;
	}
	
	
	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
				endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory());
		
		var granters = Arrays.asList(
				pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());
		
		return new CompositeTokenGranter(granters);
	}
}
