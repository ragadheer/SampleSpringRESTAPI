package com.jdpin.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value="jdPINUserDetailsService")
public class JDPINUserDetailsService implements UserDetailsService {

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
        user = jdPINUserJDBCRepository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new JDPINUserPrincipal(user);
    }
}
