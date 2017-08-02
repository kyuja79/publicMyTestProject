package com.ssocio.oauth2.util;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

public interface RestTemplateUtil {
	public <T> T postForObject(String url, MultiValueMap<String, String> map, Class<T> clazz, MediaType type) throws Exception;
}
