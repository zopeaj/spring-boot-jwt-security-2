package com.appsecurity.app.config.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.appsecurity.app.auth.UsernameAndPasswordAuthenticationRequest;
import com.appsecurity.app.config.security.jwt.properties.JwtConfigurationProperties;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUsernameAndPassworAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	private UserDetails userDetails;
	
	@Autowired 
	private JwtConfigurationProperties jwtConfigurationProps;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		}catch(IOException e) {
			throw new RuntimeException(e);
		}	
	}
	
	protected void successfulAuthenticaton(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String token = jwtService.generateToken(userDetails);
		response.addHeader(jwtConfigurationProps.getAuthorizationHeader(), jwtConfigurationProps.getTokenPrefix() + token);
	}
}
