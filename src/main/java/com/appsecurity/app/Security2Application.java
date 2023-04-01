package com.appsecurity.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.appsecurity.app.config.security.jwt.properties.JwtConfigurationProperties;



@SpringBootApplication
public class Security2Application implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(Security2Application.class);
	
	@Autowired 
	private JwtConfigurationProperties jwtConfigurationProps;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Security2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        logger.info("TokenPrefix: {}", jwtConfigurationProps.getTokenPrefix());
        logger.info("SecretKey: {}", jwtConfigurationProps.getSecretKey());
        logger.info("NumberOfDays: {}", jwtConfigurationProps.getTokenExpirationDays());
        logger.info("AuthorizationHeader: {}", jwtConfigurationProps.getAuthorizationHeader());
	}

}
