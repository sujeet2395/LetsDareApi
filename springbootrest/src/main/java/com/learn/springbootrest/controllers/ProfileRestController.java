package com.learn.springbootrest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springbootrest.model.Output;
import com.learn.springbootrest.model.Profile;
import com.learn.springbootrest.services.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileRestController {
	@Autowired
	private ProfileService profileService;
	
	@GetMapping()
	public List<Profile> getAll() {
	    return profileService.getAllProfiles();
	}
	
	@PostMapping("/login")
	public Output getByNameAndPassword(@RequestBody Profile profile) {
	    return profileService.getByNameAndPassword(profile);
	}
	@GetMapping("/{id}")
	public Optional<Profile> getById(@PathVariable int id) {
	    return profileService.getProfile(id);
	}
	
	@PostMapping()
	public Output loadProfile(@RequestBody Profile profile) {
	    return profileService.doRegister(profile);
	}
	
	@PutMapping()
	public Output update(@RequestBody Profile profile) {
	    return profileService.update(profile);
	}
	
	@DeleteMapping("/{id}")
	public Output deleteById(@PathVariable int id) {
		return profileService.deletePr(id);
	}
}
