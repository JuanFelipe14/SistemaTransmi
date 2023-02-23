package model;

import java.util.ArrayList;
import java.util.UUID;

public class Conductor {

    private UUID idConductor;

    String nombre;
    int cedula;
    int telefono;
    String direccion;


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

    public Conductor() {
        idConductor=UUID.randomUUID();
    }

    public Conductor(String nombre, int cedula, int telefono, String direccion, ArrayList<Bus> busesAsignados) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;

        idConductor=UUID.randomUUID();
    }

    public Conductor(UUID idConductor, String nombre, int cedula, int telefono, String direccion, ArrayList<Bus> busesAsignados) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
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

