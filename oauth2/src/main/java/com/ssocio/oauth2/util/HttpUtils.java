package com.ssocio.oauth2.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * HTTP 관련 유틸입니다.
 * 
 * @author null@danalssocio.com
 * @date 2016-06-23
 *
 */
public final class HttpUtils {

	private static final String UTF_8 = "UTF-8";

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 객체로부터 필드명과 필드에 들어있는 값을 추출합니다.
	 * HTTP Method가 GET방식일 때 파라미터 생성을 위해 사용합니다.
	 * 
	 * 사용예)
	 * Student student = new Student("hong gil dong", 17700102, true);
	 * HttpUtils.urlEncodeUTF8(HttpUtils.extractFieldNameValueMap(Student.class, student));
	 * 결과: ?name=hong%2Bgil%2Bdong&birthday=17700102&sex=true
	 * 
	 * @param clazz
	 * @param type
	 * @return
	 */
	public static <T> Map<String, String> extractFieldNameValueMap(Class<?> clazz, T type) {
		Map<String, String> fieldNameValueMap = new LinkedHashMap<>();

		for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
			
			Field[] declaredFields = c.getDeclaredFields();
			for (Field f : declaredFields) {
				f.setAccessible(true);
				String fieldName = f.getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}

				Object value = null;
				try {
					value = f.get(type);
				} catch (Exception e) {
					//ignore
				}
				if(value == null){
					continue;
				}
				if(value instanceof String[]){
					String [] stringParameterArray = (String [])value;
					for(int i = 0, length = stringParameterArray.length ; i < length ; i++){
						fieldNameValueMap.put(fieldName + "[" + i + "]", stringParameterArray[i]);
					}
					continue;
				}
				
				fieldNameValueMap.put(fieldName, "" + value);
			}
		}
		return fieldNameValueMap;
	}

	/**
	 * URL인코딩을 합니다.
	 * 
	 * 필드명과 값 사이에 = 을 넣어서 세팅합니다.
	 * 
	 * @param parameterMap
	 * @return
	 */
	public static String urlEncodeUTF8(Map<?, ?> parameterMap){
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		for (Map.Entry<?,?> entry : parameterMap.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
				sb.append(String.format("%s=%s", entry.getKey().toString(), urlEncodeUTF8(entry.getValue().toString())
			));
		}
		return sb.toString();
	}

	/**
	 * URL인코딩을 합니다.
	 * 
	 * @param param
	 * @return
	 */
	private static String urlEncodeUTF8(String param) {
		try {
			return java.net.URLEncoder.encode(param, UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
}

	/**
	 * 입력받은 headers 내용에 따라 list 데이터를 필터링하여 String[] 타입으로 변환합니다.
	 * CSV 파일을 생성하기 위해 사용합니다.
	 * 
	 * @param headers
	 * @param clazz
	 * @param list
	 * @return
	 */
	public static <T> List<String []> filterByHeaders(String [] headers, Class<T> clazz, List<T> list){
		final int columnSize = headers.length;
		
		Map<String, Integer> headerIndexMap = new LinkedHashMap<>(columnSize);
		Map<Field, Integer> fieldIndexMap = new LinkedHashMap<>();
		int index = 0;
		for(String s : headers){
			headerIndexMap.put(s, index++);
		}
		
		List< String [] > resultList = new ArrayList<>();
		
		for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
			Field[] declaredFields = c.getDeclaredFields();
			for (Field f : declaredFields) {
				f.setAccessible(true);
				String fieldName = f.getName();
				if(!headerIndexMap.containsKey(fieldName)){
					continue;
				}
				int columnIndex = headerIndexMap.get(fieldName);
				fieldIndexMap.put(f, columnIndex);
			}
		}
		
		
		for(T t : list){
			String [] row = new String [headerIndexMap.size()];
			for(Map.Entry<Field, Integer> m : fieldIndexMap.entrySet()){
				Object value = null;
				try {
					value = m.getKey().get(t);
				} catch (Exception e) {
					//ignore
					e.printStackTrace();
				}
				if(value == null){
					value = "";
				}
				row[m.getValue()] = value.toString();
			}
			resultList.add(row);
		}
		
		return resultList;
	}
	
	public static String entityEncode(String input){
		return HtmlUtils.htmlEscape(input);
	}
	
	public static String entityDecode(String input){
		return HtmlUtils.htmlUnescape(input);
	}

}
