package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private int cedula;
    private int telefono;
    private String direccion;
    private UUID codigo;

    @OneToMany
    List<Horario> horarioConductores = new ArrayList<>();

    public Conductor() {
        this.codigo = UUID.randomUUID();
    }

    public Conductor(String nombre, int cedula, int telefono, String direccion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigo = UUID.randomUUID();
    }

    public Conductor(String nombre, int cedula, int telefono, String direccion, UUID codigo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public List<Horario> getHorarioConductores() {
        return horarioConductores;
    }

    public void setHorarioConductores(List<Horario> horarioConductores) {
        this.horarioConductores = horarioConductores;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}

