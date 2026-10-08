package com.tuapp.authapi.presentation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.tuapp.authapi.domain.model.Expediente;

public record ExpedienteResponse(
        Long id,
        String numero,
        LocalDate fecha,
        String descripcion,
        LocalDateTime fechaCarga
        ) {

    public static ExpedienteResponse from(Expediente expediente) {
        return new ExpedienteResponse(
            expediente.getId(), 
            expediente.getNumero(), 
            expediente.getFecha(), 
            expediente.getDescripcion(), 
            expediente.getFechaCarga());
    }

}
