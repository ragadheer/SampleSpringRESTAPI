package com.jdpin.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JDPINCustomPasswordEncoder implements PasswordEncoder {
 
	@Autowired
    public User user;
	
    @Override
    public String encode(CharSequence rawPassword) {
    	System.out.println("in encode()");
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(rawPassword);
		
        String hashed =  BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
        System.out.println(hashedPassword+".."+hashed);
 //"$2y$12$9YShmaL0/K5TFYm5Via3T.jJN2vWF76f1g/58YbsRfiWXF9Hi2eHS";
        return hashed;
    }
 
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
    	System.out.println("in matches()");
    	 System.out.println(rawPassword+".."+encodedPassword);
        return rawPassword.equals(encodedPassword);
    }
 
}