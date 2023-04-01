package com.appsecurity.app.service.abstracts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appsecurity.app.repository.UserRepository;
import com.appsecurity.app.model.User;

@Service 
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
	}
	
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
