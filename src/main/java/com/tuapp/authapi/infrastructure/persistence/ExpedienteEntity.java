package com.tuapp.authapi.infrastructure.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "expedientes")
public class ExpedienteEntity {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true  , length = 50)
    private String numero;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column (nullable =  false, length = 500)
    private String descripcion;

    @Column (name ="fecha_carga", nullable = false, updatable = false)
    private LocalDateTime fechaCarga;

    protected  ExpedienteEntity(Long id, String numero, LocalDate fecha, String descripcion, LocalDateTime fechaCarga){
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.fechaCarga = fechaCarga;
    }

    public Long getId() {return id;}
    public String getNumero() {return  numero;}
    public LocalDate getFecha() {return fecha;}
    public String getDescripcion(){return descripcion;}
    public LocalDateTime getFechaCarga() { return fechaCarga;}
    
}
