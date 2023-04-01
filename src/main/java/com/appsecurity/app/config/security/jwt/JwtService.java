package com.appsecurity.app.config.security.jwt;

import java.util.Map;
import java.util.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.appsecurity.app.config.security.jwt.properties.JwtConfigurationProperties;
import com.google.common.net.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

	@Autowired
	private JwtConfigurationProperties jwtProperties;
	
	public String extractUseremail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateTokens(userDetails);
	}
	
	public Claims extractBody(String token) {
		return extractAllClaims(token);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUseremail(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private String generateTokens(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("authorities", userDetails.getAuthorities());
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtProperties.getTokenExpirationDays())))
				.signWith(getSecretKeyForSigningToken(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public SecretKey getSecretKeyForSigningToken() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSecretKeyForSigningToken())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
