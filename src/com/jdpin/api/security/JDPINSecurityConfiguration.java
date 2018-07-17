package com.jdpin.api.security;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class JDPINSecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	public static final Logger logger = Logger.getLogger(JDPINSecurityConfiguration.class);
	
    private static String REALM="MY_TEST_REALM";
    
    @Autowired
	DataSource dataSource;
    
    @Autowired
    private JDPINUserDetailsService jdPINUserDetailsService;
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    	logger.debug("configureGlobalSecurity() started for authenticating user credentials");
    	auth.authenticationProvider(authenticationProvider());
       // auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
      //  auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	logger.debug("authenticationProvider() started for authenticating user credentials with database entries");
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jdPINUserDetailsService);
        authProvider.setPasswordEncoder(new JDPINCustomPasswordEncoder());
        return authProvider;
    }

   // @Bean
   // public PasswordEncoder encoder() {
   //     return new BCryptPasswordEncoder(11);
  //  }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.debug("configure() started for securing the specified apis by authenticating user credentials");
        
      /*http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/jdpin/**").hasRole("ADMIN")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.*/
    	
    	http.csrf().disable()
    	.authorizeRequests()
        .antMatchers("/jdpin/**").hasAnyAuthority("ADMIN")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }
     
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
     
    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}