package com.tuapp.authapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tuapp.authapi.application.port.in.ExpedienteUseCase;
import com.tuapp.authapi.application.port.in.LoginUseCase;
import com.tuapp.authapi.application.port.out.ExpedienteRepository;
import com.tuapp.authapi.application.port.out.PasswordHasher;
import com.tuapp.authapi.application.port.out.TokenProvider;
import com.tuapp.authapi.application.port.out.UserRepository;
import com.tuapp.authapi.application.usecase.ExpedienteService;
import com.tuapp.authapi.application.usecase.LoginService;

@Configuration
public class UseCaseConfig {

    @Bean
    public LoginUseCase loginUseCase(UserRepository userRepository,
                                     PasswordHasher passwordHasher,
                                     TokenProvider tokenProvider) {
        return new LoginService(userRepository, passwordHasher, tokenProvider);
    }

    @Bean 
    public ExpedienteUseCase expedienteUseCase(ExpedienteRepository expedienteRepository){
        return new ExpedienteService (expedienteRepository);
    }
}