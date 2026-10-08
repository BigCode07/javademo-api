package com.tuapp.authapi.presentation.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuapp.authapi.application.port.in.ExpedienteUseCase;
import com.tuapp.authapi.application.port.in.ExpedienteUseCase.ExpedienteCommand;
import com.tuapp.authapi.domain.model.Expediente;
import com.tuapp.authapi.presentation.dto.ExpedienteRequest;
import com.tuapp.authapi.presentation.dto.ExpedienteResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tuapp.authapi.presentation.dto.PageResponse;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    private final ExpedienteUseCase expedienteUseCase;

    public ExpedienteController(ExpedienteUseCase expedienteUseCase) {
        this.expedienteUseCase = expedienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ExpedienteResponse> crear(@Valid @RequestBody ExpedienteRequest request) {
        Expediente creado = expedienteUseCase.crear(
                toCommand(request));
        return ResponseEntity
                .created(URI.create("/api/expedientes/" + creado.getId()))
                .body(ExpedienteResponse.from(creado));
    }

    @GetMapping
    public PageResponse<ExpedienteResponse> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return PageResponse.from(expedienteUseCase.listar(page, size), ExpedienteResponse::from);
    }

    @GetMapping("/{id}")
    public ExpedienteResponse obtener(@PathVariable Long id, @Valid @RequestBody ExpedienteRequest request) {
        return ExpedienteResponse.from(expedienteUseCase.obtener(id));
    }

    @PutMapping("/{id}")
    public ExpedienteResponse actualizar(@PathVariable Long id, @Valid @RequestBody ExpedienteRequest request) {
        return ExpedienteResponse.from(expedienteUseCase.actualizar(id, toCommand(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        expedienteUseCase.eliminar(id);
    }

    private ExpedienteCommand toCommand(ExpedienteRequest request) {
        return new ExpedienteCommand(request.numero(), request.fecha(), request.descripcion());
    }

}
