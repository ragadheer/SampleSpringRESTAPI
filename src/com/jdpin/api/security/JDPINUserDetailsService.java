package com.jdpin.api.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value="jdPINUserDetailsService")
public class JDPINUserDetailsService implements UserDetailsService {
	
	public static final Logger logger = Logger.getLogger(JDPINUserDetailsService.class);
    
	@Autowired
    private JDPINUserJDBCRepository jdPINUserJDBCRepository;
    
    @Autowired
    public User user;

    public JDPINUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String username) {
    	logger.debug("loadUserByUsername() started for authenticating username:"+username);
    	user = jdPINUserJDBCRepository.findByUserId(username);
    	logger.debug("user details retrieved from database:"+user+" for username:"+username);
        if (user == null) {
        	logger.debug("user not exists in the system for username:"+username);
            throw new UsernameNotFoundException(username);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new JDPINUserPrincipal(user);
    }
}
