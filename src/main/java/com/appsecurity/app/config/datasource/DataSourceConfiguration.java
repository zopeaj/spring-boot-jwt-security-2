package com.appsecurity.app.config.datasource;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableJpaRepositories(basePackages="com.appsecurity.app.repository", entityManagerFactoryRef="userEntityManager", transactionManagerRef="userTransactionManager")
public class DataSourceConfiguration {
	
	@Autowired
	Environment env;
		
	@Bean 
	@Primary
	@ConfigurationProperties(prefix="app.user.jpa")
	public JpaProperties userJpaProperties() {
		return new JpaProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="app.user.datasource") 
	public DataSourceProperties userDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean 
	@Primary 
	public DataSource userDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setUrl(userDataSourceProperties().getUrl());
	    dataSource.setUsername(userDataSourceProperties().getUsername());
	    dataSource.setPassword(userDataSourceProperties().getPassword());
	    dataSource.setDriverClassName(userDataSourceProperties().getDriverClassName());
	    return dataSource;
	}
	
	@Bean 
	@Primary
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(userJpaProperties().isShowSql());
	    vendorAdapter.setDatabase(userJpaProperties().getDatabase());
	    vendorAdapter.setDatabasePlatform(userJpaProperties().getDatabasePlatform());
	    vendorAdapter.setGenerateDdl(userJpaProperties().isGenerateDdl());
	    
		HashMap<String, Object> properties = new HashMap<String, Object>();
	    properties.put("hibernate.hbm2ddl.auto", "create");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.put("hibernate.format_sql", "true");

		
		em.setPersistenceUnitName("userdbContext");
		em.setPackagesToScan("com.appsecurity.app.model");
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaPropertyMap(properties);
		em.setDataSource(userDataSource());
		return em;
	}
	
	  @Bean
	  @Primary
	  public PlatformTransactionManager userTransactionManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(userEntityManager().getObject());
	    return transactionManager;
	  }
}
