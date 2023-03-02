package com.example.sistematransmilenio.model;

import com.example.sistematransmilenio.Enumeration.TipoUsuario;

import java.util.UUID;

public class Usuario {
    private UUID idUsuario;
    private TipoUsuario rol;
    private String nombres;
    private String apellidos;
    private Integer cedula;

    public Usuario(UUID idUsuario, TipoUsuario rol, String nombres, String apellidos, Integer cedula) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
    }

    public Usuario() {
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoUsuario getRol() {
        return rol;
    }

    public void setRol(TipoUsuario rol) {
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }
}

