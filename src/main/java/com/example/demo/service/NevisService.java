package com.example.demo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Method;
import com.example.demo.entity.NevisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;



@Service
public class NevisService {
	private String url = "createUser?clientExtId=1001";
	
	public ResponseEntity<String> createNevis(NevisUser nu) throws URISyntaxException {
			RestTemplate restTemplate = new RestTemplate();
		     String baseUrl = "http://localhost:8080/"+url;
		    URI uri = new URI(baseUrl);
		     
		    HttpHeaders headers = new HttpHeaders();   
//		    headers.set("X-COM-LOCATION", "USA");      
		 
		    HttpEntity<NevisUser> request = new HttpEntity<>(nu, headers);
		     
		    ResponseEntity<String> result =restTemplate.postForEntity(uri, request, String.class);
		     
			return result;
	}
	public String getEmployees()
	{
	    final String uri = "https://httpbin.org/get";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	     
	    System.out.println(result);
	    return result;
	}
}
