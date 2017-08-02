package com.ssocio.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

//@EnableAuthorizationServer
//@Configuration
public class OAuthorizationServerConfig {
	
	// http://localhost:9001/oauth/authorize?response_type=code&client_id=foo1&redirect_uri=http://localhost:9001/oauth/authorization&scope=read
	
	@Bean
	public TokenStore JdbcTokenStore(DataSource dataSource) {
		return new JdbcTokenStore(dataSource);
	}
	
}
