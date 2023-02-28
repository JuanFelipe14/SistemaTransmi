package org.example.busesApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Bus busHorario;
    private Conductor conductorHorario;
    private Ruta rutaHorario;

    ArrayList<DiasSemana> diasHorario;

    private UUID idHorario;

    private Date horaInicio;
    private Date horaFin;


    public Horario() {
        idHorario=UUID.randomUUID();
        diasHorario=new ArrayList<>();
    }

    public Horario(Bus busHorario, Conductor conductorHorario, Ruta rutaHorario, UUID idHorario, ArrayList<DiasSemana> diasHorario,Date horaFin,Date horaInicio) {
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.rutaHorario = rutaHorario;
        this.idHorario = idHorario;
        this.diasHorario=diasHorario;
        this.horaFin=horaFin;
        this.horaInicio=horaInicio;
    }

    public Horario(Bus busHorario, Conductor conductorHorario, Ruta rutaHorario,  ArrayList<DiasSemana> diasHorario,Date horaFin,Date horaInicio) {
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.rutaHorario = rutaHorario;
        idHorario=UUID.randomUUID();
        this.horaFin=horaFin;
        this.horaInicio=horaInicio;
    }

    public Bus getBusHorario() {
        return busHorario;
    }

    public void setBusHorario(Bus busHorario) {
        this.busHorario = busHorario;
    }

    public Conductor getConductorHorario() {
        return conductorHorario;
    }

    public void setConductorHorario(Conductor conductorHorario) {
        this.conductorHorario = conductorHorario;
    }

    public Ruta getRutaHorario() {
        return rutaHorario;
    }

    public void setRutaHorario(Ruta rutaHorario) {
        this.rutaHorario = rutaHorario;
    }
}
