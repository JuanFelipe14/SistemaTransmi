package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Horario;
import com.example.sistematransmilenio.model.dto.HorarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.HorarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> listHorarios (){return horarioRepository.findAll();}

    public void guardarHorario(Horario horario){
        horarioRepository.save(horario);
    }


    public List<HorarioDto> listHorariosDto() {
        List<HorarioDto> horarios = new ArrayList<>();
        for(Horario horarioCompleto: this.listHorarios()){
            HorarioDto newHorario = new HorarioDto(
                    horarioCompleto.getId(),
                    horarioCompleto.getBusHorario().getPlaca(),
                    horarioCompleto.getConductorHorario().getNombre(),
                    horarioCompleto.getDiasSemana(),
                    horarioCompleto.getHoraInicioStr(),
                    horarioCompleto.getHoraFinStr()
                    );
            horarios.add(newHorario);
        }
        return horarios;
    }
}
