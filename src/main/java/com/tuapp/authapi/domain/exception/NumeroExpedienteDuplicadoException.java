package com.tuapp.authapi.domain.exception;

public class NumeroExpedienteDuplicadoException extends RuntimeException{
    public NumeroExpedienteDuplicadoException(String numero){
        super("Ya existe un expediente con el numero " + numero);
    }
}
