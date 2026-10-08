package com.tuapp.authapi.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuapp.authapi.application.port.in.LoginUseCase;
import com.tuapp.authapi.application.port.in.LoginUseCase.LoginCommand;
import com.tuapp.authapi.application.port.in.LoginUseCase.LoginResult;
import com.tuapp.authapi.presentation.dto.LoginRequest;
import com.tuapp.authapi.presentation.dto.LoginResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase){
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
       LoginResult result = loginUseCase.login(
        new LoginCommand(request.email(),request.password()));
        return new LoginResponse(result.accessToken(),"Bearer");
       
    }
    
}
