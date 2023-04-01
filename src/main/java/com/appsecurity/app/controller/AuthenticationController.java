package com.appsecurity.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appsecurity.app.auth.AuthenticationResponse;
import com.appsecurity.app.auth.RegisterRequest;
import com.appsecurity.app.service.concretes.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService service;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		AuthenticationResponse response = service.register(request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> login() {
		return ResponseEntity.ok("Login");
	}
}
