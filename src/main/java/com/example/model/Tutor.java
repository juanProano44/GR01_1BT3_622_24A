package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tutor")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(mappedBy = "tutor")
    private List<Tutoria> tutorias;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tutoria> getTutorias() {
        return tutorias;
    }

    public void setTutorias(List<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }
}
