package com.learn.springbootrest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springbootrest.apikey.model.ApiClient;
import com.learn.springbootrest.apikey.model.ConstantValue;
import com.learn.springbootrest.apikey.services.ApiKeysServices;
import com.learn.springbootrest.apikey.util.JwtCreator;
import com.learn.springbootrest.model.AuthenticationRequest;
import com.learn.springbootrest.model.AuthenticationResponse;
import com.learn.springbootrest.model.SubjectContainer;
import com.learn.springbootrest.services.MyUserDetailsService;
import com.learn.springbootrest.util.JwtUtil;
import com.learn.springbootrest.util.SubjectWrapper;

@RestController
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtCreator jwtCreator;

	@Autowired
	private SubjectWrapper subWrapper;
	
	@Autowired
	private ApiKeysServices apiKeysServices;
	
	@GetMapping("/hello")
	public String hello() {
	    return "Hello";
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(	new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}	
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		ApiClient apiClient=null;
		if(!authenticationRequest.getApiKey().equals(ConstantValue.APIKEYDEMO)) {
			apiClient=apiKeysServices.getApiClientByKey(authenticationRequest.getApiKey());
			if(apiClient!=null && apiClient.isExpired())
			{
				//throw new Exception("ApiKey is expired");
				LOGGER.info("ApiKey is expired");
				return ResponseEntity.ok("ApiKey is expired");
			}
		}
		String subjectContainerStr=subWrapper.convertSubjectToJson(new SubjectContainer(authenticationRequest.getUsername(),authenticationRequest.getApiKey()));
		final String jwt = jwtCreator.generateToken(subjectContainerStr);
		LOGGER.info("auth login done : "+authenticationRequest.toString());
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ResponseEntity<?> adminAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(	new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}	
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		ApiClient apiClient=null;
		if(!authenticationRequest.getApiKey().equals(ConstantValue.APIKEYDEMO)) {
			apiClient=apiKeysServices.getApiClientByKey(authenticationRequest.getApiKey());
			if(apiClient!=null && apiClient.isExpired())
			{
				//throw new Exception("ApiKey is expired");
				LOGGER.info("ApiKey is expired");
				return ResponseEntity.ok("ApiKey is expired");
			}
		}
		String subjectContainerStr=subWrapper.convertSubjectToJson(new SubjectContainer(authenticationRequest.getUsername(),authenticationRequest.getApiKey()));
		final String jwt = jwtCreator.generateToken(subjectContainerStr);
		LOGGER.info("auth login done : "+authenticationRequest.toString());
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
