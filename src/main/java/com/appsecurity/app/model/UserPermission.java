package com.appsecurity.app.model;

public enum UserPermission {
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	FEEDS_READ("feed:read"),
	FEEDS_WRITE("feed:write");
	
	private String permission;
	
	UserPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
}
