package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.dto.EstacionDto;
import com.example.sistematransmilenio.repository.EstacionRepository;
import com.example.sistematransmilenio.repository.HorarioRepository;
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
                    e.getNombre()
            );
            nuevasEstaciones.add(nuevaEstacion);
        }
        return nuevasEstaciones;
    }

    public void eliminarEstacion(long id) {
        estacionRepository.deleteById(id);
    }

    public Estacion findEstacionById(Long id) {
        return estacionRepository.findById(id).get();
    }

    public Estacion update(Estacion estacion) {
        return estacionRepository.save(estacion);
    }
}
