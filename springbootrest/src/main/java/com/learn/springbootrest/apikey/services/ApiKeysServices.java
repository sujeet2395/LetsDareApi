package com.learn.springbootrest.apikey.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.springbootrest.apikey.model.ApiClient;
import com.learn.springbootrest.apikey.model.ApiGrant;
import com.learn.springbootrest.apikey.model.ConstantValue;
import com.learn.springbootrest.apikey.repository.ApiClientRepository;
import com.learn.springbootrest.apikey.util.RandomStringGenerator;

@Service
public class ApiKeysServices {
	
	@Autowired
	ApiClientRepository apiClientRepo;
	
	public List<ApiClient> getApiClients()
	{
		return apiClientRepo.findAll();
	}
	
	public ApiClient createApiClient(ApiClient apiClient) {
		ApiClient ac=new ApiClient(apiClient);
		apiClientRepo.save(ac);
		return ac;
	}
	
	public ApiClient getApiClientByKey(String apiKey)
	{
		Optional<ApiClient> c=apiClientRepo.findByAPIKey(apiKey);
		if(c!=null)
			return c.get();
		return new ApiClient();
	}
	
	public List<ApiClient> getApiClientByName(String name)
	{
		List<ApiClient> c=apiClientRepo.findByName(name);
		return c;
	}
	
	public ApiClient getApiClientById(Long id)
	{
		Optional<ApiClient> c= apiClientRepo.findById(id);
		if(c.isPresent())
			return c.get();
		return new ApiClient();
	}
	public ApiClient updateClient(ApiClient apiClient)
	{
		Optional<ApiClient> apiClientExisted=apiClientRepo.findById(apiClient.getId());
		if(apiClientExisted.isPresent()) {
			ApiClient apiClientExistedObj=apiClientExisted.get();
			if(apiClient.getAPIKey()!=null && !apiClient.getAPIKey().trim().isEmpty())
			{
				apiClientExistedObj.setAPIKey(apiClient.getAPIKey());
			}
			if(apiClient.getCreatedAt()!=null && !apiClient.getCreatedAt().trim().isEmpty())
			{
				apiClientExistedObj.setCreatedAt(apiClient.getCreatedAt());
			}
			if(apiClient.getExpireAt()!=null && !apiClient.getExpireAt().trim().isEmpty())
			{
				apiClientExistedObj.setExpireAt(apiClient.getExpireAt());
			}
			if(apiClient.getName()!=null && !apiClient.getName().trim().isEmpty())
			{
				apiClientExistedObj.setName(apiClient.getName());
			}
			if(apiClient.getGrant()!=null && (apiClient.getGrant().equals(ApiGrant.APPACCOUNT) || apiClient.getGrant().equals(ApiGrant.APPFULLACCESS)))
			{
				apiClientExistedObj.setGrant(apiClient.getGrant());
			}
			return apiClientRepo.save(apiClientExistedObj);
		}
		apiClient.setId(-1l);
		return apiClient;
	}
	
	public boolean deleteApiClient(Long id)
	{
		if(apiClientRepo.findById(id)!=null) {
			apiClientRepo.deleteById(id);
			return true;
		}
		return false;
	}
	 
}
