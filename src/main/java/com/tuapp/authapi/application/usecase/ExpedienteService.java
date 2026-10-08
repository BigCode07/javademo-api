package com.tuapp.authapi.application.usecase;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.tuapp.authapi.application.common.PageResult;
import com.tuapp.authapi.application.port.in.ExpedienteUseCase;
import com.tuapp.authapi.application.port.out.ExpedienteRepository;
import com.tuapp.authapi.domain.exception.ExpedienteNotFoundException;
import com.tuapp.authapi.domain.exception.NumeroExpedienteDuplicadoException;
import com.tuapp.authapi.domain.model.Expediente;

public class ExpedienteService implements ExpedienteUseCase {

    private static final int MAX_PAGE_SIZE = 100;

    private final ExpedienteRepository expedienteRepository;

    public ExpedienteService (ExpedienteRepository expedienteRepository){
        this.expedienteRepository = expedienteRepository;
    }

    @Override 
    public  Expediente crear (ExpedienteCommand command){
        Expediente nuevo = new Expediente(null,command.numero(),command.fecha(),command.descripcion(), LocalDateTime.now());

        if(expedienteRepository.existsByNumero(nuevo.getNumero())){
            throw new NumeroExpedienteDuplicadoException(nuevo.getNumero());
        }
        return expedienteRepository.save(nuevo);
    }

    @Override 
    public Expediente obtener (Long id){
        return expedienteRepository.findById(id)
        .orElseThrow(() -> new ExpedienteNotFoundException(id));
    }

    @Override  
    public PageResult<Expediente> listar (int page, int size){
        if(page < 0){
            throw new IllegalArgumentException("La pagina no puede ser negativa");
        }
        if(size < 1 || size > MAX_PAGE_SIZE){
            throw new IllegalArgumentException("El tamaño debe estar entre 1 y " + MAX_PAGE_SIZE);
        }

        return expedienteRepository.findAll(page, size);
    }
    
    @Override 
    public Expediente actualizar(Long id, ExpedienteCommand command){
        Expediente existente = obtener(id);

        Expediente actualizado = new Expediente(
            id,command.numero(),command.fecha(),command.descripcion(),existente.getFechaCarga());

        if(expedienteRepository.existsByNumeroAndIdNot(actualizado.getNumero(), id)){
            throw new NumeroExpedienteDuplicadoException(actualizado.getNumero());
        }
        return expedienteRepository.save(actualizado);
    }

    @Override 
    public void eliminar (Long id){
        if (!expedienteRepository.existsById(id)){
            throw new ExpedienteNotFoundException(id);
        }
        expedienteRepository.deleteById(id);
    }
}
