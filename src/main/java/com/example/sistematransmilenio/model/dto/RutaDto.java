package com.example.sistematransmilenio.model.dto;

import java.util.List;

public class RutaDto {
    private Long id;
    private String nombreRuta;
    private List<String> estaciones;

    public RutaDto(Long id, String nombreRuta, List<String> estaciones) {
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

    public List<String> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<String> estaciones) {
        this.estaciones = estaciones;
    }

    @Override
    public String toString() {
        return "RutaDto{" +
                "id=" + id +
                ", nombreRuta='" + nombreRuta + '\'' +
                ", estaciones=" + estaciones +
                '}';
    }
}
