package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Horario;
import com.example.sistematransmilenio.model.Ruta;
import com.example.sistematransmilenio.model.dto.HorarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.HorarioRepository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private RutaService rutaService;


    public List<Horario> listHorarios (){return horarioRepository.findAll();}

    public void guardarHorario(Horario horario){
        horarioRepository.save(horario);
    }


    public List<HorarioDto> listHorariosDto() {
        List<HorarioDto> horarios = new ArrayList<>();
        for(Horario horario: this.listHorarios()){
            HorarioDto newHorario = new HorarioDto(
                    horario.getId(),
                    horario.getBusHorario().getPlaca(),
                    horario.getConductorHorario().getNombre(),
                    horario.getConductorHorario().getId(),
                    horario.getDiasSemana(),
                    horario.getHoraInicioStr(),
                    horario.getHoraFinStr(),
                    horario.getRutaHorario().getNombreRuta(),
                    horario.getRutaHorario().getId()
                    );
            horarios.add(newHorario);
        }
        return horarios;
    }

    public HorarioDto findHorarioDtoById(Long id) {
        Horario horario = this.findById(id);
        return this.horarioToHorarioDto(horario);
    }

    public HorarioDto horarioToHorarioDto(Horario horario) {
        return new HorarioDto(
                horario.getId(),
                horario.getBusHorario().getPlaca(),
                horario.getConductorHorario().getNombre(),
                horario.getConductorHorario().getId(),
                horario.getDiasSemana(),
                horario.getHoraInicioStr(),
                horario.getHoraFinStr(),
                horario.getRutaHorario().getNombreRuta(),
                horario.getRutaHorario().getId()

        );
    }

    private Horario findById(Long id) {
        return this.horarioRepository.findById(id).get();
    }

    public Horario horarioDtoToHorario(HorarioDto horario) {
        Horario horarioReturn = new Horario();
        horarioReturn.setHoraFinStr(horario.getHoraFinStr());
        horarioReturn.setHoraInicioStr(horario.getHoraInicioStr());
        horarioReturn.setConductorHorario(conductorService.findById(horario.getIdConductorHorario()));
        horarioReturn.setRutaHorario(rutaService.findById(horario.getIdRutaHorario()));
        horarioReturn.setBusHorario(busService.findBusByPlaca(horario.getBusHorario()));
        horarioReturn.setDiasSemana(horario.getDiasSemana());
        horarioReturn.setId(horario.getId());
        Calendar cal = Calendar.getInstance();
        cal.set(0,0,0,0,0,0);

        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horario.getHoraInicioStr()));

        Date dateInicio = cal.getTime();
        cal.set(0,0,0,0,0,0);
        cal.set(Calendar.HOUR_OF_DAY,  Integer.parseInt(horario.getHoraFinStr()));
        Date dateFin = cal.getTime();
        horarioReturn.setHoraFin(dateFin);
        horarioReturn.setHoraInicio(dateInicio);

        return horarioReturn;

    }

    public Horario save(Horario horarioSave) {
        if(this.validarBuses(horarioSave)){
            return this.horarioRepository.save(horarioSave);
        }else
            return null;

    }

    private boolean validarBuses(Horario horarioSave) {
        List<Horario> horariosBus = new ArrayList<>();
        for(Horario h:listHorarios()){
            if(h.getBusHorario().getPlaca().equals(horarioSave.getBusHorario().getPlaca())){
                horariosBus.add(h);
            }
        }
        for(Horario h:horariosBus){
            if(h.getDiasSemana().equals(horarioSave.getDiasSemana())){
                if(Integer.parseInt(h.getHoraFinStr()) > Integer.parseInt(horarioSave.getHoraInicioStr())&&Integer.parseInt(h.getHoraInicioStr())<Integer.parseInt(horarioSave.getHoraFinStr())){
                    return false;
                }
            }
        }
        return true;
    }

    public Horario update(Horario horarioSave) {
        return this.horarioRepository.save(horarioSave);
    }

    public boolean eliminarHorario(Long id) {
        try{
            this.horarioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
