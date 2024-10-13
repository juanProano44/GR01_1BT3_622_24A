package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    private int codigomateria;
    private String nombre;
    private String descripcion;

    // Getters y Setters
    public int getCodigomateria() {
        return codigomateria;
    }

    public void setCodigomateria(int codigomateria) {
        this.codigomateria = codigomateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
