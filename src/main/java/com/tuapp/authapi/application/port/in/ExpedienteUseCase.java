package com.tuapp.authapi.application.port.in;

import java.time.LocalDate;

import com.tuapp.authapi.application.common.PageResult;
import com.tuapp.authapi.domain.model.Expediente;

public interface ExpedienteUseCase {
    
    Expediente crear (ExpedienteCommand command);

    Expediente obtener (Long id);

    Expediente actualizar (Long id, ExpedienteCommand  command);

    void eliminar (Long id);

    PageResult<Expediente> listar (int page, int size);

    record ExpedienteCommand(String numero, LocalDate fecha, String descripcion){}


}
