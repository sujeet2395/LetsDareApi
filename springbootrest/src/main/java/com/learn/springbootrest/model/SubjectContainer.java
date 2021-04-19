package com.learn.springbootrest.model;

public class SubjectContainer {
	private String username;
	private String apiKey;
	
	public SubjectContainer() {
		
	}
	public String getUsername() {
		return username;
	}
	public SubjectContainer(String username, String apiKey) {
		this.username = username;
		this.apiKey = apiKey;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
}
