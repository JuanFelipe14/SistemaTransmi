package com.example.sistematransmilenio.model.dto;

import java.util.List;

public class RutaDto {
    private Long id;
    private String nombreRuta;
    private List<EstacionDto> estaciones;

    public RutaDto(Long id, String nombreRuta, List<EstacionDto> estaciones) {
        this.id = id;
        this.nombreRuta = nombreRuta;
        this.estaciones = estaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public List<EstacionDto> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<EstacionDto> estaciones) {
        this.estaciones = estaciones;
    }
}
