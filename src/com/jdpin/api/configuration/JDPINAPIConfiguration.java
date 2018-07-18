package com.jdpin.api.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jdpin.api.interceptor.JDPINExecuteTimeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jdpin.api")
public class JDPINAPIConfiguration extends WebMvcConfigurerAdapter {
	
	public static final Logger logger = Logger.getLogger(JDPINAPIConfiguration.class);
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new JDPINExecuteTimeInterceptor());
	}
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		logger.debug("dataSource() setup started");
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    driverManagerDataSource.setUrl("jdbc:oracle:thin:@10.22.107.2:1521:PINDWHD1");
	    driverManagerDataSource.setUsername("PIN_WS");
	    driverManagerDataSource.setPassword("webservice18");
	    return driverManagerDataSource;
	}
}