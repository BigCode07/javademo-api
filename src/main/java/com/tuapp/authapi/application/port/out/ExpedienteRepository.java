package com.tuapp.authapi.application.port.out;

import java.util.Optional;

import com.tuapp.authapi.application.common.PageResult;
import com.tuapp.authapi.domain.model.Expediente;

public interface ExpedienteRepository {

    Expediente save (Expediente expediente);

    Optional<Expediente> findById (Long id);

    PageResult<Expediente> findAll(int page, int size);

    boolean existsById (Long id);

    boolean existsByNumero(String numero);

    boolean existsByNumeroAndIdNot(String numero, Long id);

    void deleteById (Long id);

}
