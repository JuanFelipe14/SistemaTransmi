package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.RutaRepository;

@Service
public class RutaService {

    @Autowired
    RutaRepository rutaRepository;
    public Iterable<Ruta> listarRutas (){return rutaRepository.findAll();}
}
