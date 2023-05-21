package com.example.sistematransmilenio.model;

import com.example.sistematransmilenio.Enumeration.DiasSemana;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Bus busHorario;
    @ManyToOne
    private Conductor conductorHorario;
    @ManyToOne
    private Ruta rutaHorario;


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



    public Horario(Bus busHorario, Conductor conductorHorario, Ruta rutaHorario,DiasSemana diasHorario, Date horaFin, Date horaInicio) {
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.rutaHorario = rutaHorario;
        this.diasSemana=diasHorario;
        this.horaFin=horaFin;
        this.horaInicio=horaInicio;
    }
    //TODO constructor para crear fechas a partir de str

    public Horario(){}
    public Horario(Bus busHorario, Conductor conductorHorario, Ruta rutaHorario, DiasSemana diasHorario,String horaFinStr,String horaInicioStr) {
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.rutaHorario = rutaHorario;

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

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", busHorario=" + busHorario +
                ", conductorHorario=" + conductorHorario +
                ", rutaHorario=" + rutaHorario +
                ", diasSemana=" + diasSemana +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", horaInicioStr='" + horaInicioStr + '\'' +
                ", horaFinStr='" + horaFinStr + '\'' +
                '}';
    }
}
