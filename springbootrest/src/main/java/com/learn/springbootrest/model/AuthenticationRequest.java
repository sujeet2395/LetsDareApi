package com.learn.springbootrest.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	private String username;
    private String password;
    private String apiKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	//need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
    
    @Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", apiKey=" + apiKey + "]";
	}
}