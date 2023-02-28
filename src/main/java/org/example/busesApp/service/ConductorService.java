package org.example.busesApp.service;

import org.example.busesApp.model.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.busesApp.repository.ConductorRepository;

import java.util.List;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    public List<Conductor> listarConductores (){return conductorRepository.findAll();}
}
