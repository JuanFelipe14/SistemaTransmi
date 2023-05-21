package com.example.sistematransmilenio.model.dto;

public class EstacionDto {
    private long id;
    private String nombre;

    private Long mapKey;

    public EstacionDto(long id, String nombre, Long mapKey) {
        this.id = id;
        this.nombre = nombre;
        this.mapKey = mapKey;
    }

    public Long getMapKey() {
        return mapKey;
    }

    public void setMapKey(Long mapKey) {
        this.mapKey = mapKey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
