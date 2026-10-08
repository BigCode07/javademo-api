package com.tuapp.authapi.presentation.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExpedienteRequest(
        @NotBlank(message = "El número de expediente es obligatorio")
        @Size(max = 50, message = "El número no puede superar los 50 caracteres")
        String numero,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate fecha,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion) {
}