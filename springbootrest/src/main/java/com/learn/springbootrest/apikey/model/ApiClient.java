package com.learn.springbootrest.apikey.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.learn.springbootrest.apikey.util.RandomStringGenerator;
import com.sun.istack.NotNull;

@Entity
@Table(name="apiclients")
@Component
public class ApiClient{
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@NotNull
	private String name;
	
	@NotNull 
	@Column(name="apikeys", unique=true)
	private String APIKey;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ApiGrant apigrant;
	
	@NotNull
	private String createdAt;
	
	@NotNull
	private String expireAt;
	
	public ApiClient() {
		
	}
	public ApiClient(String name, String apiAccessBy, String apiAccessType) {
		this.name = (name==null?"RESTInterface":name);
		String prefix=(apiAccessBy.equals("WEB")?ConstantValue.AKWEBPREFIX:
			(apiAccessBy.equals("AND")?ConstantValue.AKANDPREFIX:(apiAccessBy.equals("RES")?ConstantValue.AKRESPREFIX:"")));
		String apiKey=(prefix.equals("")?ConstantValue.APIKEYDEMO:RandomStringGenerator.nextString(ConstantValue.APIKEYLENGTH, prefix));
		this.APIKey=apiKey;
		this.apigrant=(apiAccessType.equals("APPFULLACCESS")?ApiGrant.APPFULLACCESS:ApiGrant.APPACCOUNT);
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss z");
		this.createdAt=formatter.format(new Date());
		this.expireAt= formatter.format(new Date(System.currentTimeMillis()+1000*60*60));
	}
	public ApiClient(String name, String apiAccessBy) {
		this(name, apiAccessBy, "APPACCOUNT");
	}
	public ApiClient(String name) {
		this(name, "WEB", "APPACCOUNT");
	}
	public ApiClient(ApiClient apiClient)
	{
		this(apiClient.getName(),(apiClient.getAPIKey()==null?"":apiClient.getAPIKey()),(apiClient.getGrant()==null?"":apiClient.getGrant().toString()));
	}
	public ApiClient(String name, ApiGrant apigrant) {
		this.name = name;
		this.apigrant=apigrant;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAPIKey() {
		return APIKey;
	}

	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}


	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}
	/*public boolean isExpire()
	{
		return if(new Date(expireAt)
	}*/
	public ApiGrant getGrant() {
		return apigrant;
	}

	public void setGrant(ApiGrant grant) {
		this.apigrant = grant;
	}

	@Override
	public String toString() {
		return "ApiClient [id=" + id + ", name=" + name + ", APIKey=" + APIKey + ", apigrant=" + apigrant + ", createdAt="
				+ createdAt + ", expireAt=" + expireAt + "]";
	}
	public boolean isExpired() {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss z");
		try {
			Date expireAtDate=formatter.parse(expireAt);
			Date currentDate=new Date(System.currentTimeMillis());
			if(expireAtDate.before(currentDate))
				return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
