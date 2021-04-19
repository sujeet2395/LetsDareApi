package com.learn.springbootrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.learn.springbootrest.filters.JwtRequestFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebSecurity
@EnableSwagger2
public class RestApiSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    	
    }
    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addRedirectViewController("/my/path/", "/my/path")
        .setKeepQueryParams(true)
        .setStatusCode(HttpStatus.PERMANENT_REDIRECT); 
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /*
    	http.authorizeRequests()
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/users/byname/{name}").hasRole("ADMIN")
                .antMatchers("/users/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll();
                .and().formLogin();*/
                //.and().csrf().disable().headers().frameOptions().disable();
    	http.csrf().disable().authorizeRequests()
    		.antMatchers(HttpMethod.PUT,"/users").hasAnyAuthority("ADMIN","USER")
    		.antMatchers(HttpMethod.DELETE,"/users").hasAnyAuthority("ADMIN", "USER")
    		.antMatchers("/users").hasAuthority("ADMIN")
    		.antMatchers("/users/{id}").hasAnyAuthority("ADMIN", "USER")
    		//.antMatchers(HttpMethod.GET,"/users").hasAuthority("ADMIN")
    		//.antMatchers(HttpMethod.GET,"/users/byname/{name}").hasAuthority("ADMIN")
    		.antMatchers("/api/**").hasAuthority("ADMIN")
            //.antMatchers(HttpMethod.POST,"/users").permitAll()
            .antMatchers("/users/hello").permitAll()
            .antMatchers("/hello").permitAll()
    		.antMatchers(HttpMethod.POST,"/login").permitAll()
			.antMatchers(HttpMethod.POST,"/admin").permitAll()
			.antMatchers("/**").permitAll()
			.and()
			.exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    } 

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
