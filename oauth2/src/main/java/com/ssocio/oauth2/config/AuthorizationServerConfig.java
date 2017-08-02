package com.ssocio.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    private static String REALM="MY_OAUTH_REALM";

	@Bean
	public TokenStore JdbcTokenStore(DataSource dataSource) {
		return new JdbcTokenStore(dataSource);
	}
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	
	/*
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// OAuth2 인증서버 자체의 보안 정보를 설정하는 부분
//		security.realm(REALM+"/client");
		security.checkTokenAccess("isAuthenticated()");
	}
	*/
	
	// 인증서버 token 발급 부분 설정
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// OAuth2 서버가 작동하기 의한 Endpoint에 대한 정보를 설정		
		endpoints.authenticationManager(authenticationManager);
	}
	
	@Override 
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// Client 에 대한 정보를 설정 하는 부분
		clients.inMemory()
		
			// 클라이언트 ID
			.withClient("foo1")	//foo1
			// 클라이언트 secret
			.secret("bar2")	//bar2
			
			// 기본은 다섯개
			// 여기 속성이 없으면 인증 불가
			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit", "client_credentials")
			
			// 클라이언트에 부여된 권한
			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			
			// 이 클라이언트로 접근할수 있는 범위 제한
			// 해당 클라이언트로 API를 접근 했을 때 접근 범위를 제한 시키는 속성
			.scopes("read", "write", "trust")
			
			// 이 클라이언트로 발급된 액세스 토큰의 시간 (단위:초)
			.accessTokenValiditySeconds(60 * 60 * 4) // 4시간
			
			// 이 클라이언트로 발급된 리프레시토큰의 시간(단위:초)
			.refreshTokenValiditySeconds(60 * 60 * 24 * 120)
			
			.autoApprove(true)
			.and();
			
	} 
	
}
