package com.learn.springbootrest.apikey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.springbootrest.apikey.model.ApiClient;


public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {
	public Optional<ApiClient> findByAPIKey(String apikey);
	public List<ApiClient> findByName(String name);
}
