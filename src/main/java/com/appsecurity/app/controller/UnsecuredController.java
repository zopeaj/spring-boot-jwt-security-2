package com.appsecurity.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import com.appsecurity.app.model.User;

@RestController
@RequestMapping("/products/")
public class UnsecuredController {
	Set<? extends GrantedAuthority> grantedAuthorites;
	String password = "pass";
	String name = "name";
	String firstName = "data";
	String lastName = "david";
	String email = "david@gmail.com";
	String username = "datasam";
	private User user1 = new User(grantedAuthorites, password, name, lastName, firstName, email, username, false, false, false, false);
	private User user2 = new User(grantedAuthorites, password, name, lastName, firstName, email, username, false, false, false, false);
	private List<User> USERS = Arrays.asList(user1, user2);
	
	@GetMapping("users")
	public ResponseEntity<List<User>> postUser() {
		return ResponseEntity.ok(this.USERS);
	}

	@PostMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(this.USERS);
	}
}
