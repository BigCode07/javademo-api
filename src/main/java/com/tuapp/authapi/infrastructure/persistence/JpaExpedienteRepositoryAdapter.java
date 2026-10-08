package com.tuapp.authapi.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.tuapp.authapi.application.common.PageResult;
import com.tuapp.authapi.application.port.out.ExpedienteRepository;
import com.tuapp.authapi.domain.model.Expediente;

@Component
public class JpaExpedienteRepositoryAdapter implements ExpedienteRepository {

    private final SpringDataExpedienteRepository repository;

    public JpaExpedienteRepositoryAdapter(SpringDataExpedienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Expediente save(Expediente expediente) {
        return toDomain(repository.save(toEntity(expediente)));
    }

    @Override
    public Optional<Expediente> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public PageResult<Expediente> findAll(int page, int size) {
        Page<ExpedienteEntity> result = repository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fechaCarga")));

        return new PageResult<>(
                result.getContent().stream().map(this::toDomain).toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNumero(String numero) {
        return repository.existsByNumero(numero);
    }

    @Override
    public boolean existsByNumeroAndIdNot(String numero, Long id) {
        return repository.existsByNumeroAndIdNot(numero, id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Expediente toDomain(ExpedienteEntity entity) {
        return new Expediente(entity.getId(), entity.getNumero(), entity.getFecha(),
                entity.getDescripcion(), entity.getFechaCarga());
    }

    private ExpedienteEntity toEntity(Expediente expediente) {
        return new ExpedienteEntity(expediente.getId(), expediente.getNumero(), expediente.getFecha(),
                expediente.getDescripcion(), expediente.getFechaCarga());
    }
}