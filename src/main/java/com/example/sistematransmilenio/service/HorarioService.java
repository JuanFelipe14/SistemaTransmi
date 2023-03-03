package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.HorarioRepository;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> listHorarios (){return horarioRepository.findAll();}

    public void guardarHorario(Horario horario){
        horarioRepository.save(horario);
    }


}
