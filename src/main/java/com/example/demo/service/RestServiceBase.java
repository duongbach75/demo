package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestServiceBase {
	@Autowired
	private Environment env;
	
	public Object execute(String url, String method, RequestData data) throws Exception{
		try {
			String serviceUrl = env.getProperty("nevis.idm.url") + url;
			// Create header
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);;

						/**
						 * Build request
						 */
						HttpEntity<Object> entity = new HttpEntity<>(data.getBodyParams(), headers);
						RestTemplate restTemplate = new RestTemplate();
						// Build Request URI
						if (data.getPathParams().isEmpty()) {
							return restTemplate.exchange(serviceUrl, HttpMethod.valueOf(method), entity, String.class, Object.class).getBody();
//							UriComponents uriBuilder = UriComponentsBuilder.fromUriString(serviceUrl)
//									.buildAndExpand(data.getPathParams());
//							serviceUrl = uriBuilder.toUriString();
						}
						// Create entity for request

						if (data.getQueryParams()==null) {
							return restTemplate.exchange(serviceUrl, HttpMethod.valueOf(method), entity, String.class, Object.class).getBody();
						}
						return restTemplate.exchange(serviceUrl, HttpMethod.valueOf(method), entity, String.class, Object.class,
								data.getQueryParams()).getBody();
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						throw new Exception(e.getMessage());
					}	
		
	}
}
