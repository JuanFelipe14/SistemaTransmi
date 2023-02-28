package org.example.busesApp.service;

import org.example.busesApp.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.busesApp.repository.HorarioRepository;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> getHorarios (){return horarioRepository.findAll();}
}
