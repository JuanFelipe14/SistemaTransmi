package com.example.sistematransmilenio.model;

import com.example.sistematransmilenio.Enumeration.DiasSemana;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
public class Horario {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Bus busHorario;
    @ManyToOne
    private Conductor conductorHorario;
    @ManyToOne
    private Ruta rutaHorario;

    private UUID idHorario;

    private DiasSemana diasSemana;
    private Date horaInicio;
    private Date horaFin;
    private String horaInicioStr;
    private String horaFinStr;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiasSemana getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(DiasSemana diasSemana) {
        this.diasSemana = diasSemana;
    }

    public UUID getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(UUID idHorario) {
        this.idHorario = idHorario;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }



    public String getHoraInicioStr() {
        return horaInicioStr;
    }

    public void setHoraInicioStr(String horaInicioStr) {
        this.horaInicioStr = horaInicioStr;
    }

    public String getHoraFinStr() {
        return horaFinStr;
    }

    public void setHoraFinStr(String horaFinStr) {
        this.horaFinStr = horaFinStr;
    }

    public Horario() {
        idHorario=UUID.randomUUID();
    }

    public Horario(Bus busHorario, Conductor conductorHorario, Ruta rutaHorario, UUID idHorario, ArrayList<DiasSemana> diasHorario, Date horaFin, Date horaInicio) {
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.rutaHorario = rutaHorario;
        this.idHorario = idHorario;
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
