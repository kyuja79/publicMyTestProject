package com.ssocio.oauth2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssocio.oauth2.util.RestTemplateUtil;
import com.ssocio.oauth2.vo.AccessTokenInfo;

/**
 * 권한 코드를 위해 만든 컨트롤러
 */
@RestController
@RequestMapping("oauth")
public class OAuthController {
private static final Logger log = LoggerFactory.getLogger(OAuthController.class);
	
	@Autowired
	private RestTemplateUtil restTemplateUtil;
	
	@Autowired
	private ObjectMapper oMapper;
	
	@RequestMapping("authorization")
	public AccessTokenInfo authorization(@RequestParam("code") String code) {
		String curl = String.format("curl " +
				"-F \"grant_type=authorization_code\" " +
				"-F \"code=%s\" " +
				"-F \"scope=read\" " +
				"-F \"client_id=foo1\" " +
				"-F \"client_secret=bar2\" " +
				"-F \"redirect_uri=http://localhost:9001/oauth/authorization\" " +
				"\"http://foo1:bar2@localhost:9001/oauth/token\"", code);
		System.out.println(curl);
		
		/*
		AuthorizationInfo authInfo = new AuthorizationInfo();
		authInfo.setGrant_type("authorization_code");
		authInfo.setCode(code);
		authInfo.setScope("read");
		authInfo.setClient_id("foo1");
		authInfo.setClient_secret("bar2");
		authInfo.setRedirect_uri("http://localhost:9001/oauth/authorization");
		log.info("--------------------------------------------------------------");
		log.info(authInfo.toString());
		log.info("--------------------------------------------------------------");
		*/
		AccessTokenInfo accessToken = null;
		try {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.set("grant_type", "authorization_code");
			map.set("code", code);
			map.set("scope", "read");
			map.set("client_id", "foo1");
			map.set("client_secret", "bar2");
			map.set("redirect_uri", "http://localhost:9001/oauth/authorization");
			accessToken = restTemplateUtil.postForObject("http://foo1:bar2@localhost:9001/oauth/token", map, AccessTokenInfo.class, MediaType.APPLICATION_FORM_URLENCODED);
			log.info("###############################################################");
			log.info(accessToken.toString());
			log.info("###############################################################");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	@RequestMapping("authorization-code")
	@ResponseBody
	public String authorizationCode(@RequestParam("code") String code) {
		String curl = String.format("curl " +
				"-F \"grant_type=authorization_code\" " +
				"-F \"code=%s\" " +
				"-F \"scope=read\" " +
				"-F \"client_id=foo1\" " +
				"-F \"client_secret=bar2\" " +
				"-F \"redirect_uri=http://localhost:9001/oauth/authorization-code\" " +
				"\"http://foo1:bar2@localhost:9001/oauth/token\"", code);
		
		System.out.println(curl);
		return curl;
	}
}
