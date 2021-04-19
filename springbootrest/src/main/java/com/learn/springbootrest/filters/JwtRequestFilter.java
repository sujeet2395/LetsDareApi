package com.learn.springbootrest.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learn.springbootrest.apikey.model.ApiClient;
import com.learn.springbootrest.apikey.model.ConstantValue;
import com.learn.springbootrest.apikey.services.ApiKeysServices;
import com.learn.springbootrest.apikey.util.JwtCreator;
import com.learn.springbootrest.controllers.HomeController;
import com.learn.springbootrest.model.SubjectContainer;
import com.learn.springbootrest.services.MyUserDetailsService;
import com.learn.springbootrest.util.JwtUtil;
import com.learn.springbootrest.util.SubjectWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
	
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtCreator jwtCreator;
    
    @Autowired
    private SubjectWrapper subWrapper;
    
    @Autowired
    private ApiKeysServices apiKeysServices;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String subjectContainerStr = null;
        SubjectContainer subjectContainer=null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            subjectContainerStr = jwtCreator.extractSubject(jwt);
            subjectContainer=(SubjectContainer)subWrapper.getSubjectFromJson(subjectContainerStr,SubjectContainer.class);
        }


        if (subjectContainer!=null && subjectContainer.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(subjectContainer.getUsername());
            String subjectConStr="";
            if(subjectContainer.getApiKey().equals(ConstantValue.APIKEYDEMO)) {
            	subjectConStr=subjectContainerStr;
            	LOGGER.info("Demo apikey is used");
            }
            else {
            	ApiClient apiClient = apiKeysServices.getApiClientByKey(subjectContainer.getApiKey());
            	if(apiClient!=null && !apiClient.isExpired())
            		subjectConStr=subWrapper.convertSubjectToJson(new SubjectContainer(userDetails.getUsername(),apiClient.getAPIKey()));
            }

            if (!subjectConStr.equals("") && jwtCreator.validateToken(jwt, subjectConStr)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                //System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                LOGGER.info("Is authenticated user: "+SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            }
        }
        chain.doFilter(request, response);
    }

}