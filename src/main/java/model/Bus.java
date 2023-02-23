package model;

import java.util.ArrayList;
import java.util.UUID;

public class Bus {
    private String placa;

    private UUID idBus;
    private String modelo;


    public Bus() {
        this.placa = " ";
        this.modelo = " ";

        idBus=UUID.randomUUID();
    }

    public Bus(String placa, UUID idBus, String modelo, ArrayList<Ruta> rutasAsignadas) {
        this.placa = placa;
        this.idBus = idBus;
        this.modelo = modelo;

    }

    public Bus(String placa, String modelo, ArrayList<Ruta> rutasAsignadas) {
        this.placa = placa;
        this.modelo = modelo;

        idBus=UUID.randomUUID();
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
