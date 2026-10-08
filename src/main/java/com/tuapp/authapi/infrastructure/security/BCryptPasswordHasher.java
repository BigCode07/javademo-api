package com.tuapp.authapi.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tuapp.authapi.application.port.out.PasswordHasher;

@Component 
public class BCryptPasswordHasher  implements  PasswordHasher{

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override 
    public String hash(String rawPassword){
        return  encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword,String passwordHash){
        return  encoder.matches(rawPassword, passwordHash);
    }
}
