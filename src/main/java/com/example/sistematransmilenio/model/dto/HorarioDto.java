package com.example.sistematransmilenio.model.dto;

import com.example.sistematransmilenio.Enumeration.DiasSemana;

public class HorarioDto {

    private Long id;

    private String busHorario;

    private String conductorHorario;
    private Long idConductorHorario;
    private String rutaHorario;
    private Long idRutaHorario;

    private DiasSemana diasSemana;

    private String horaInicioStr;

    private String horaFinStr;

    public HorarioDto(Long id, String busHorario, String conductorHorario, Long idConductorHorario, DiasSemana diasSemana, String horaInicioStr, String horaFinStr,String rutaHorario, Long idRutaHorario) {
        this.rutaHorario= rutaHorario;
        this.idRutaHorario=idRutaHorario;
        this.id = id;
        this.busHorario = busHorario;
        this.conductorHorario = conductorHorario;
        this.diasSemana = diasSemana;
        this.horaInicioStr = horaInicioStr;
        this.horaFinStr = horaFinStr;
        this.idConductorHorario = idConductorHorario;
    }

    public Long getIdConductorHorario() {
        return idConductorHorario;
    }

    public void setIdConductorHorario(Long idConductorHorario) {
        this.idConductorHorario = idConductorHorario;
    }

    public String getRutaHorario() {
        return rutaHorario;
    }

    public void setRutaHorario(String rutaHorario) {
        this.rutaHorario = rutaHorario;
    }

    public Long getIdRutaHorario() {
        return idRutaHorario;
    }

    public void setIdRutaHorario(Long idRutaHorario) {
        this.idRutaHorario = idRutaHorario;
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
