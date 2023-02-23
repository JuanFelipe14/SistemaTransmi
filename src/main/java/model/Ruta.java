package model;

import java.util.ArrayList;
import java.util.UUID;

public class Ruta {

    private UUID idRuta;
    private int codigo;
    private ArrayList<String> estaciones;
    private String horarios;

    public Ruta(int codigo, ArrayList<String> estaciones, String horarios) {
        this.codigo = codigo;
        this.estaciones = estaciones;
        this.horarios = horarios;
        idRuta=UUID.randomUUID();
    }

    public Ruta(UUID idRuta, int codigo, ArrayList<String> estaciones, String horarios) {
        this.idRuta = idRuta;
        this.codigo = codigo;
        this.estaciones = estaciones;
        this.horarios = horarios;
    }

    public Ruta() {
        estaciones= new ArrayList<>();
        idRuta=UUID.randomUUID();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<String> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(ArrayList<String> estaciones) {
        this.estaciones = estaciones;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
}

