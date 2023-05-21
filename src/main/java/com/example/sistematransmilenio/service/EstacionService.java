package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.dto.EstacionDto;
import com.example.sistematransmilenio.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstacionService {
    @Autowired
    private EstacionRepository estacionRepository;

    public List<Estacion> listarEstaciones() {
        return estacionRepository.findAll();
    }

    public List<EstacionDto> listEstacionesDto() {
        List<EstacionDto> nuevasEstaciones = new ArrayList<>();
        for(Estacion e: this.listarEstaciones()){
            EstacionDto nuevaEstacion= new EstacionDto(
                    e.getId(),
                    e.getNombre(),
                    e.getMapKeyNumber()
            );
            nuevasEstaciones.add(nuevaEstacion);
        }
        return nuevasEstaciones;
    }

    public boolean eliminarEstacion(long id) {
        try{
            Estacion estacionNueva = this.findEstacionById(id);
            List<Estacion> estaciones = this.listarEstaciones();
            for(Estacion est: estaciones){
                if(est.getMapKeyNumber() > estacionNueva.getMapKeyNumber()){
                    est.setMapKeyNumber(est.getMapKeyNumber()-1);
                    estacionRepository.save(est);
                }
            }

            estacionRepository.deleteById(id);


        }catch (Exception e){
            return false;
        }
        return true;

    }

    public Estacion findEstacionById(Long id) {
        return estacionRepository.findById(id).get();
    }

    public Estacion update(Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    public Estacion save(Estacion estacionNueva) {
        boolean encontrado = false;
        List<Estacion> estaciones = this.listarEstaciones();
        for(Estacion est: estaciones){
            if(est.getMapKeyNumber() >= estacionNueva.getMapKeyNumber()){
                est.setMapKeyNumber(est.getMapKeyNumber()+1);
                estacionRepository.save(est);
            }
        }

        return estacionRepository.save(estacionNueva);
    }

    public Estacion findEstacionByNombre(String s) {
        return estacionRepository.findByNombre(s);
    }
}
