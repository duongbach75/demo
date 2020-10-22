package com.example.demo.service;

import java.util.Map;



public class RequestData {
	private Object bodyParams;
	private Map<String, String> pathParams;
	private Map<String, String> queryParams;
	public Object getBodyParams() {
		return bodyParams;
	}
	public void setBodyParams(Object bodyParams) {
		this.bodyParams = bodyParams;
	}
	public Map<String, String> getPathParams() {
		return pathParams;
	}
	public void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	public Map<String, String> getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}
	public RequestData(Object bodyParams, Map<String, String> pathParams, Map<String, String> queryParams) {
		super();
		this.bodyParams = bodyParams;
		this.pathParams = pathParams;
		this.queryParams = queryParams;
	}
}
