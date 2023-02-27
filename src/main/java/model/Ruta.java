package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Ruta {

    @Id
    @GeneratedValue
    private Long id;

    private UUID idRuta;
    private int codigo;
    private ArrayList<String> estaciones;
    private String horarios;

    @OneToMany
    List<Horario> horarioRuta = new ArrayList<>();

    public Ruta(int codigo, ArrayList<String> estaciones, String horarios) {
        this.codigo = codigo;
        this.estaciones = estaciones;
        this.horarios = horarios;
        this.idRuta = UUID.randomUUID();
    }

    public Ruta(UUID idRuta, int codigo, ArrayList<String> estaciones, String horarios) {
        this.idRuta = idRuta;
        this.codigo = codigo;
        this.estaciones = estaciones;
        this.horarios = horarios;
    }

    public Ruta() {
        estaciones= new ArrayList<>();
        idRuta=UUID.randomUUID();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<String> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(ArrayList<String> estaciones) {
        this.estaciones = estaciones;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(UUID idRuta) {
        this.idRuta = idRuta;
    }

    public List<Horario> getHorarioRuta() {
        return horarioRuta;
    }

    public void setHorarioRuta(List<Horario> horarioRuta) {
        this.horarioRuta = horarioRuta;
    }
}

