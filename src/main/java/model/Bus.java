package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Bus {

    @Id
    @GeneratedValue
    private Long id;

    private String placa;
    private UUID idBus;
    private String modelo;

    @OneToMany
    List<Horario> horarioBus = new ArrayList<>();

    public Bus() {
        this.placa = " ";
        this.modelo = " ";
        this.idBus = UUID.randomUUID();
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
