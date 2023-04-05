package com.example.sistematransmilenio.model.dto;

import com.example.sistematransmilenio.Enumeration.DiasSemana;

public class HorarioDto {

    private Long id;

    private String busHorario;

    private String conductorHorario;

    private DiasSemana diasSemana;

    private String horaInicioStr;

    private String horaFinStr;

    public HorarioDto(Long id, String busHorario, String conductorHorario, DiasSemana diasSemana, String horaInicioStr, String horaFinStr) {
        this.id = id;
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.diasSemana = diasSemana;
        this.horaInicioStr = horaInicioStr;
        this.horaFinStr = horaFinStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusHorario() {
        return busHorario;
    }

    public void setBusHorario(String busHorario) {
        this.busHorario = busHorario;
    }

    public String getConductorHorario() {
        return conductorHorario;
    }

    public void setConductorHorario(String conductorHorario) {
        this.conductorHorario = conductorHorario;
    }

    public DiasSemana getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(DiasSemana diasSemana) {
        this.diasSemana = diasSemana;
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
}
