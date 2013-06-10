package com.sample.rest.controller.test.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * This helper class provides wrapper api for Spring RESTTemplate calls 
 * 
 * @author Naveen
 *
 */
public class RestHelper {

	RestTemplate restTemplate = new RestTemplate();
	
	public String post(String url, HttpHeaders requestHeaders, MultiValueMap<String, Object> parameters) throws UnsupportedEncodingException {
		
		encode(parameters);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(parameters, requestHeaders);
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		return result.getBody();
		
	}
	
	public String put(String url, HttpHeaders requestHeaders, MultiValueMap<String, String> parameters) throws UnsupportedEncodingException {
		
		encodeParam(parameters);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, requestHeaders);
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
		return result.getBody();
		
	}
	
	public String delete(String url, HttpHeaders requestHeaders, MultiValueMap<String, String> parameters) throws UnsupportedEncodingException {
		
		encodeParam(parameters);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, requestHeaders);
		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
		return result.getBody();
		
	}
	
	public ResponseEntity<String> get(String url, HttpHeaders requestHeaders, MultiValueMap<String, String> parameters) throws UnsupportedEncodingException {
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, requestHeaders);
		String params = "";
		if(parameters != null) {
			params = null;
			for (String key : parameters.keySet()) {
				String value = parameters.get(key).get(0);
				if (params == null) {
					params = "?" + key + "=" + URLEncoder.encode(value, "utf-8");
				} else {
					params += "&" + key + "=" + URLEncoder.encode(value, "utf-8");
				}
			}
		}
		
		ResponseEntity<String> result = restTemplate.exchange(url+(params==null?"":params), HttpMethod.GET, requestEntity, String.class);
		return result;
		
	}
	
	private void encodeParam(MultiValueMap<String, String> postParameters) throws UnsupportedEncodingException {
		for(String key : postParameters.keySet()) {
			String value = postParameters.get(key).get(0);
			postParameters.set(key, URLEncoder.encode(value, "utf-8"));
		}
	}
	
	private void encode(MultiValueMap<String, Object> postParameters) throws UnsupportedEncodingException {
		for(String key : postParameters.keySet()) {
			
			if (postParameters.get(key).get(0) instanceof String) {
				String value = (String) postParameters.get(key).get(0);
				postParameters.set(key, URLEncoder.encode(value, "utf-8"));
			}	
			
		}
	}
	
}
