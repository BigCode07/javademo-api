package com.tuapp.authapi.domain.exception;

public class InvalidCredentialsException extends  RuntimeException{
    public InvalidCredentialsException() {
        super("Credenciales inválidas");
    }
}
