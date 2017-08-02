package com.ssocio.oauth2;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssocio.oauth2.util.RestTemplateUtil;
import com.ssocio.oauth2.vo.AccessTokenInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 인증관리서버
 * @author user@danalssocio.com
 * @date 2017. 6. 2.
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Oauth2Application{ 
	
//	/**
//	 * mappings
//	 */
//	@Value("${oauth.paths.token:/oauth/authorize}")
//    private String tokenPath = "/oauth/token";
//
//    @Value("${oauth.paths.token_key:/oauth/token_key}")
//    private String tokenKeyPath = "/oauth/token_key";
//
//    @Value("${oauth.paths.check_token:/oauth/check_token}")
//    private String checkTokenPath = "/oauth/check_token";
//
//    @Value("${oauth.paths.authorize:/oauth/authorize}")
//    private String authorizePath = "/oauth/authorize";
//
//    @Value("${oauth.paths.confirm:/oauth/confirm_access}")
//    private String confirmPath = "/oauth/confirm_access";
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private ServerProperties server;
//	
//    /**
//     * configure
//     */
//	@Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        String prefix = server.getServletPrefix();
//        endpoints.prefix(prefix);
//        // @formatter:off
//        endpoints.authenticationManager(authenticationManager)
//                .pathMapping("/oauth/confirm_access", confirmPath)
//                .pathMapping("/oauth/token", tokenPath)
//                .pathMapping("/oauth/check_token", checkTokenPath)
//                .pathMapping("/oauth/token_key", tokenKeyPath)
//                .pathMapping("/oauth/authorize", authorizePath);
//        // @formatter:on
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // @formatter:off
//        clients.inMemory()
//                .withClient("my-trusted-client")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read", "write", "trust")
//                .resourceIds("sparklr")
//                .accessTokenValiditySeconds(60)
//                .and()
//                .withClient("my-client-with-registered-redirect")
//                .authorizedGrantTypes("authorization_code")
//                .authorities("ROLE_CLIENT")
//                .scopes("read", "trust")
//                .resourceIds("sparklr")
//                .redirectUris("http://anywhere?key=value")
//                .and()
//                .withClient("my-client-with-secret")
//                .authorizedGrantTypes("client_credentials", "password")
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                .scopes("read")
//                .resourceIds("sparklr")
//                .secret("secret");
//        // @formatter:on
//    }
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
}




