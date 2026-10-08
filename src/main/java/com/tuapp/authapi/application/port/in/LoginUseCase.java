package com.tuapp.authapi.application.port.in;

public interface LoginUseCase {
    LoginResult login (LoginCommand command);
    record LoginCommand(String email, String password){}
    record LoginResult (String accessToken){}
}
