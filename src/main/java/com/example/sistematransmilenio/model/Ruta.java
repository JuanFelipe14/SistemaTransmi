package com.example.sistematransmilenio.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombreRuta;
    private int codigo;

    @ManyToMany
    @JoinTable(name = "estaciones_ruta",
            joinColumns = @JoinColumn(name = "estacion_id"),
            inverseJoinColumns = @JoinColumn(name = "ruta_id"))
    private List<Estacion> estaciones= new ArrayList<>();
    private String horarios;

    @OneToMany
    List<Horario> horarioRuta = new ArrayList<>();

    public Ruta(int codigo, ArrayList<Estacion> estaciones, String horarios) {
        this.codigo = codigo;
        this.estaciones = estaciones;
        this.horarios = horarios;

    }

    public Ruta() {
        estaciones= new ArrayList<>();

    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Horario> getHorarioRuta() {
        return horarioRuta;
    }

    public void setHorarioRuta(List<Horario> horarioRuta) {
        this.horarioRuta = horarioRuta;
    }
}

