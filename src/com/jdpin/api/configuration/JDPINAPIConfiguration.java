package com.jdpin.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jdpin.api")
public class JDPINAPIConfiguration {
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    driverManagerDataSource.setUrl("jdbc:oracle:thin:@10.22.107.2:1521:PINDWHD1");
	    driverManagerDataSource.setUsername("PIN_WS");
	    driverManagerDataSource.setPassword("webservice18");
	    return driverManagerDataSource;
	}
}