package com.appsecurity.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String encode(String password) {
		return passwordEncoder.encode(password);
	}
	
	public boolean decode(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
