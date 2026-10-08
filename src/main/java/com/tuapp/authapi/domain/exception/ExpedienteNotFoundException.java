package com.tuapp.authapi.domain.exception;

public class ExpedienteNotFoundException extends RuntimeException{
    public ExpedienteNotFoundException(Long id){
        super("No existe el expediente con el id "  + id);
    }
}
