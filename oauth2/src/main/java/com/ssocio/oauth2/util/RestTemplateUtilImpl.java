package com.ssocio.oauth2.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtilImpl implements RestTemplateUtil {
	
	@Override
	public <T> T postForObject(String url, MultiValueMap<String, String> map, Class<T> clazz, MediaType type) throws Exception {
		int timeout = 1500;
		RestTemplate restTemplate = new RestTemplate(getFactory(timeout));
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, getHeaders(type));
		HttpEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
		return responseEntity.getBody();
	}
	
	public HttpComponentsClientHttpRequestFactory getFactory(int timeOut){
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(timeOut);
		factory.setConnectTimeout(timeOut);
		return factory;
	}
	
	public HttpHeaders getHeaders(MediaType type){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		return headers;
	}

}
