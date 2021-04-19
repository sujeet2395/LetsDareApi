package com.learn.springbootrest.apikey.model;

public enum ApiGrant {
	APPFULLACCESS("APPFULLACCESS"), APPACCOUNT("APPACCOUNT");
	
	private String apigrant;
	private ApiGrant(String apigrant)
	{
		this.apigrant=apigrant;
	}
	public String getApigrant() {
		return apigrant;
	}
	public void setApigrant(String apigrant) {
		this.apigrant = apigrant;
	}
}
