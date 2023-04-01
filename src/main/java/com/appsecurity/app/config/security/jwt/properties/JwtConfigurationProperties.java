package com.appsecurity.app.config.security.jwt.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.google.common.net.HttpHeaders;


@Configuration
@ConfigurationProperties(prefix="app.user.jwt")
public class JwtConfigurationProperties {
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;
	
	public String getSecretKey() {
		return this.secretKey;
	}
	
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	public String getTokenPrefix() {
		return this.tokenPrefix;
	}
	
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	
	public Integer getTokenExpirationDays() {
		return this.tokenExpirationAfterDays;
	}
	
	public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
		this.tokenExpirationAfterDays = tokenExpirationAfterDays;
	}
	
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
}
