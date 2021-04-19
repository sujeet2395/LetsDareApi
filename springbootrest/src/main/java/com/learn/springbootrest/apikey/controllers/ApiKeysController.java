package com.learn.springbootrest.apikey.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springbootrest.apikey.model.ApiClient;
import com.learn.springbootrest.apikey.services.ApiKeysServices;

@RestController
@RequestMapping("/api")
public class ApiKeysController {
	
	@Autowired
	ApiKeysServices apiKeysServices;
	
	@GetMapping
	public List<ApiClient> getApiClients()
	{
		return apiKeysServices.getApiClients();
	}
	@GetMapping("/{id}")
	public ApiClient getApiClientById(@PathVariable Long id)
	{
		return apiKeysServices.getApiClientById(id);
	}
	@GetMapping("/byak/{apiKey}")
	public ApiClient getApiClientByKey(@PathVariable String apiKey)
	{
		return apiKeysServices.getApiClientByKey(apiKey);
	}
	@GetMapping("/byname/{name}")
	public List<ApiClient> getApiClientByName(@PathVariable String name)
	{
		return apiKeysServices.getApiClientByName(name);
	}
	
	@PostMapping()
	public ApiClient createApiClient(@RequestBody ApiClient apiClient)
	{
		return apiKeysServices.createApiClient(apiClient);
	}
	@PutMapping()
	public ApiClient updateApiClient(@RequestBody ApiClient apiClient)
	{
		return apiKeysServices.updateClient(apiClient);
	}
	@DeleteMapping("/{id}")
	public boolean deleteApiClient(@PathVariable Long id)
	{
		return apiKeysServices.deleteApiClient(id);
	}
}
