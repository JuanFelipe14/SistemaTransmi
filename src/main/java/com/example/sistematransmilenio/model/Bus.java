package com.example.sistematransmilenio.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "placa", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private String placa;
    private UUID idBus;

    @Column(name = "modelo", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private String modelo;

    @OneToMany
    List<Horario> horarioBus = new ArrayList<>();

    public Bus() {
        this.placa = " ";
        this.modelo = " ";
        this.idBus = UUID.randomUUID();
    }

    public List<Horario> getHorarioBus() {
        return horarioBus;
    }

    public void setHorarioBus(List<Horario> horarioBus) {
        this.horarioBus = horarioBus;
    }

    public Bus(String placa, UUID idBus, String modelo, ArrayList<Ruta> rutasAsignadas) {
        this.placa = placa;
        this.idBus = idBus;
        this.modelo = modelo;
    }

    public Bus(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
        this.idBus = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdBus() {
        return idBus;
    }

    public void setIdBus(UUID idBus) {
        this.idBus = idBus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


}
