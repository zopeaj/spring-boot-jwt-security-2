package com.appsecurity.app.model;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;

import static com.appsecurity.app.model.UserPermission.*;

public enum UserRole {
	USER(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, FEEDS_READ, FEEDS_WRITE));

	private final Set<UserPermission> permissions;
	
	UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<UserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions()
					.stream() 
					.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
					.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
