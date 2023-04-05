package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.ConductorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    public List<Conductor> listarConductores (){return conductorRepository.findAll();}

    public Conductor recuperarConductor(Long id) { return  conductorRepository.findById(id).get();
    }

    public void guardarConductor(Conductor conductor) {
        conductorRepository.save(conductor);
    }

    public Conductor findConductorByCedula(int cedula){
        return conductorRepository.findConductorByCedula(cedula);
    }

    public List<Conductor> buscarPorNombre(String textoBusqueda) {
        return conductorRepository.findConductorByNameStartingWith(textoBusqueda);
    }

    public void eliminarConductor(long id ){
        conductorRepository.deleteById(id);
    }
}