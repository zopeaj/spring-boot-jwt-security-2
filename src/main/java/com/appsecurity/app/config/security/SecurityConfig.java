package com.appsecurity.app.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsecurity.app.config.security.jwt.JwtAuthorizationFilter;
import com.appsecurity.app.config.security.jwt.JwtUsernameAndPassworAuthenticationFilter;

//import com.appsecurity.app.config.security.jwt.JwtUsernameAndPassworAuthenticationFilter;
//import com.appsecurity.app.config.security.jwt.JwtAuthorizationFilter;
//import com.appsecurity.app.config.security.jwt.JwtUsernameAndPassworAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	//private JwtUsernameAndPassworAuthenticationFilter jwtUsernameAndPassword;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
		//http
			//.authorizeHttpRequests()
			//.requestMatchers("/admin/**").permitAll()
		 		//.and()
		 	//.authorizeHttpRequests()
			//.requestMatchers("/api/v1/auth/**").permitAll()
				//.anyRequest()
				//.authenticated()
				//.and()
			//.sessionManagement()
			//.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//		.and()
			//.authenticationProvider(authenticationProvider);
			//.addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		//return http.build();
        //http.authorizeHttpRequests()
        //.requestMatchers("/products/**").permitAll()
        	//.and()
        //.authorizeHttpRequests()
        //.requestMatchers("/api/v1/auth/**").hasRole("ADMIN")
        	//.anyRequest()
        //.authenticated()
        	//.and()
        //.httpBasic();
        
        //return http.build();
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/v1/auth/**")
			.permitAll()
			.anyRequest()
			.authenticated()
				.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
