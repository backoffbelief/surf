package com.kael.surf.mvc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@PropertySource("classpath:/config/properties/jdbc.properties")
public class DataSourceConfig {
	
	@Value("${jdbc.driver}")
	private String driverClass;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	
	
	@Bean(name="dataSource")
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

}
