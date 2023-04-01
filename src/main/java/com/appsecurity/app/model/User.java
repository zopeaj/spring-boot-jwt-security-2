package com.appsecurity.app.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;


@Entity
@Table(name="user_tb")
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String password;
	private String name;
	private String lastName;
	private String firstName;
	private String email;
	private String username;
	private final boolean isAccountNonExpired;
	private final boolean isAccountNonLocked;
	private final boolean isCredentialsNonExpired;
	private final boolean isEnabled;
	private Collection<? extends GrantedAuthority> grantedAuthorities;
	
	public User(Collection<? extends GrantedAuthority> grantedAuthorities, 
			String password, 
			String name, 
			String lastName, 
			String firstName, 
			String email, String 
			username, 
			boolean isAccountNonExpired,
			boolean isAccountNonLocked,
			boolean isCredentialsNonExpired,
			boolean isEnabled) {
		this.grantedAuthorities = grantedAuthorities;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return email;
	}
	
	public boolean isAccountNonExpired() {
		return false;
	}

	public boolean isAccountNonLocked() {
		return false;
	}

	public boolean isCredentialsNonExpired() {
		return false;
	}

	public boolean isEnabled() {
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
