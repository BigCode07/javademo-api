package com.tuapp.authapi.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expediente {
    private final Long id;
    private final String numero;
    private final LocalDate fecha;
    private final String descripcion;
    private  final LocalDateTime fechaCarga;
    
    public Expediente(Long id, String numero, LocalDate fecha, String descripcion, LocalDateTime fechaCarga) {
        
        if(numero == null || numero.isBlank()){
            throw new IllegalArgumentException("El numero de expediente es obligatorio");
        }
        if(fecha == null){
            throw new IllegalArgumentException("La fehca es obligatoria");
        }
        if(descripcion == null || descripcion.isBlank()){
            throw new IllegalArgumentException( "La descripcion es obligatoria");
        }
        
        
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.fechaCarga = fechaCarga;
    }

    public Long getId(){return id;}
    public String getNumero(){return numero;}
    public LocalDate getFecha(){return  fecha;}
    public String getDescripcion(){return descripcion;}
    public LocalDateTime getFechaCarga(){return fechaCarga;} 

    
}
