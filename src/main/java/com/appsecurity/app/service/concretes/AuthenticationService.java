package com.appsecurity.app.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.appsecurity.app.auth.AuthenticationRequest;
import com.appsecurity.app.auth.AuthenticationResponse;
import com.appsecurity.app.auth.RegisterRequest;
import com.appsecurity.app.config.security.PasswordService;
import com.appsecurity.app.config.security.jwt.JwtService;
import com.appsecurity.app.model.User;
import com.appsecurity.app.service.abstracts.UserService;
import static com.appsecurity.app.model.UserRole.*;


@Service
public class AuthenticationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User(USER.getGrantedAuthorities(), 
				passwordService.encode(request.getPassword()), 
				request.getName(), 
				request.getLastName(), 
				request.getFirstName(), 
				request.getEmail(), 
				request.getUsername(),
				true, true, true, true
			);
		userService.saveUser(user);
		String jwtToken = jwtService.generateToken(user);
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(jwtToken);
		return response;
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
	    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
	    var user = userService.findByEmail(request.getEmail()).orElseThrow();
	    String jwtToken = jwtService.generateToken(user);
	    AuthenticationResponse response = new AuthenticationResponse();
	    response.setToken(jwtToken);
	    return response;
	}
}
