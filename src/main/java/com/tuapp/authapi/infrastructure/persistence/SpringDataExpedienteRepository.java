package com.tuapp.authapi.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataExpedienteRepository  extends  JpaRepository<ExpedienteEntity,Long>{
    boolean existsByNumero(String numero);
    
    boolean existsByNumeroAndIdNot(String numero, Long id);

}
