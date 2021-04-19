package com.learn.springbootrest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springbootrest.model.AuthenticationRequest;
import com.learn.springbootrest.model.AuthenticationResponse;
import com.learn.springbootrest.model.User;
import com.learn.springbootrest.repository.UserRepository;
import com.learn.springbootrest.services.MyUserDetailsService;
import com.learn.springbootrest.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository repository;

	@GetMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}
	
	@GetMapping()
	public List<User> getAll() {
	    return repository.findAll();
	}
	
	@GetMapping("/byname/{name}")
	public List<User> getUserByName(@PathVariable String name) {
	    return repository.findByName(name);
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable long id) {
	    return repository.findById(id);
	}
	
	@PostMapping()
	public User loadUser(@RequestBody User user) {
	    return repository.save(user);
	}
	
	@PutMapping()
	public User updateUser(@RequestBody User user) {
		User existed_user=repository.findById(user.getId()).orElse(new User());
		
		if(existed_user!=null && existed_user.getId()>0)
		{
			boolean isAdmin=SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
			if(user.getRoles()!=null && user.getRoles().trim().isEmpty()==false && isAdmin && existed_user.isActive())
			{
				existed_user.setRoles(user.getRoles());
				
			}
			if(isAdmin)
			{
				existed_user.setActive(user.isActive());
			}
			
			if(user.getFirst_name()!=null && user.getFirst_name().trim().isEmpty()==false)
			{
				existed_user.setFirst_name(user.getFirst_name());
				
			}
			if(user.getLast_name()!=null && user.getLast_name().trim().isEmpty()==false)
			{
				existed_user.setLast_name(user.getLast_name());
				
			}
			if(user.getName()!=null && user.getName().trim().isEmpty()==false && user.getName().equals(user.getFirst_name()+" "+user.getLast_name()))
			{
				existed_user.setName(user.getName());
				
			}
			if(user.getUsername()!=null && user.getUsername().trim().isEmpty()==false)
			{
				existed_user.setUsername(user.getUsername());
			}
			if(user.getEmail_id()!=null && user.getEmail_id().trim().isEmpty()==false)
			{
				existed_user.setEmail_id(user.getEmail_id());
			}
			if(user.getPasswd()!=null && user.getPasswd().trim().isEmpty()==false)
			{
				existed_user.setPasswd(user.getPasswd());
			}
			if(user.getMobile_no()!=null && user.getMobile_no().trim().isEmpty()==false)
			{
				existed_user.setMobile_no(user.getMobile_no());
			}
			return repository.save(existed_user);
		}
	    return existed_user;
	}
	
	@DeleteMapping("/{id}")
	public User deleteUserById(@PathVariable long id) {
		User existed_user=repository.findById(id).orElse(new User());
		User deletedU=new User();
		if(existed_user!=null && existed_user.getId()>0)
		{
			repository.deleteById(id);			
			deletedU.setId(id);
		}else {
			deletedU.setId((long)-1);
		}
		return deletedU;
	}
	
}
