package org.example.busesApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int codigo;


    //private ArrayList<String> estaciones;
    private String horarios;

    public Ruta(int codigo, ArrayList<String> estaciones, String horarios) {
        this.codigo = codigo;
       // this.estaciones = estaciones;
        this.horarios = horarios;
    }



    public Ruta() {
    //    estaciones= new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
/*
    public ArrayList<String> getEstaciones() {
        return estaciones;
    }


    public void setEstaciones(ArrayList<String> estaciones) {
        this.estaciones = estaciones;
    }*/

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
}

