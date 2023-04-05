package com.example.sistematransmilenio.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotNull(message = "no puede ser nulo")
    private String nombre;

    @ManyToMany(mappedBy = "estaciones")
    private List<Ruta> rutas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }



    public Estacion() {
    }


    public Estacion(String nombre) {
        this.nombre = nombre;
    }
}
