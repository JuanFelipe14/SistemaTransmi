package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Iterable<Horario> getHorarios (){return horarioRepository.findAll();}
}
