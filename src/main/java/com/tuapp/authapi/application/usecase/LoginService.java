package com.tuapp.authapi.application.usecase;

import com.tuapp.authapi.application.port.in.LoginUseCase;
import com.tuapp.authapi.application.port.out.PasswordHasher;
import com.tuapp.authapi.application.port.out.TokenProvider;
import com.tuapp.authapi.application.port.out.UserRepository;
import com.tuapp.authapi.domain.exception.InvalidCredentialsException;
import com.tuapp.authapi.domain.model.User;

public class LoginService implements  LoginUseCase{
    
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public LoginService (UserRepository userRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider){
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenProvider = tokenProvider;
    }

    @Override 
    public LoginResult login (LoginCommand command){
        User user = userRepository.findByEmail(command.email().trim().toLowerCase())
        .orElseThrow(InvalidCredentialsException::new);

        if(!user.isActive() || !passwordHasher.matches(command.password(),user.getPasswordHash())){
            throw new InvalidCredentialsException();
        }

        return new LoginResult(tokenProvider.generate(user));
    }
}
