package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.ConductorRepository;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    public Iterable<Conductor> listarConductores (){return conductorRepository.findAll();}
}
