package com.jdpin.api.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JDPINCustomPasswordEncoder implements PasswordEncoder {
	
	public static final Logger logger = Logger.getLogger(JDPINCustomPasswordEncoder.class);
	
	@Autowired
    public User user;
	
    @Override
    public String encode(CharSequence rawPassword) {
    	logger.debug("encode() started");
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(rawPassword);
		
        String hashed =  BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
        logger.debug(hashedPassword+".."+hashed);
 //"$2y$12$9YShmaL0/K5TFYm5Via3T.jJN2vWF76f1g/58YbsRfiWXF9Hi2eHS";
        return hashed;
    }
 
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
    	logger.debug("matches() started");
    	logger.debug(rawPassword+".."+encodedPassword);
        return rawPassword.equals(encodedPassword);
    }
 
}