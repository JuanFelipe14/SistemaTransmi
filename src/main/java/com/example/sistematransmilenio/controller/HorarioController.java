package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.Enumeration.DiasSemana;
import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.model.Horario;
import com.example.sistematransmilenio.model.Ruta;
import com.example.sistematransmilenio.model.dto.BusDto;
import com.example.sistematransmilenio.model.dto.HorarioDto;
import com.example.sistematransmilenio.service.BusService;
import com.example.sistematransmilenio.service.ConductorService;
import com.example.sistematransmilenio.service.HorarioService;
import com.example.sistematransmilenio.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    HorarioService horarioService;
    @Autowired
    ConductorService conductorService;
    @Autowired
    BusService busService;
    @Autowired
    RutaService rutaService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<HorarioDto> listarHorarios() {
        List<HorarioDto> horarios = horarioService.listHorariosDto();
        //model.addAttribute("horarios", horarios);
        return horarios;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/view/{id}")
    public HorarioDto verHorario(@PathVariable("id") Long id) {
        HorarioDto horarioRetorno = horarioService.findHorarioDtoById(id);
        return horarioRetorno;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/add")
    public HorarioDto agregarHorario(@Valid @RequestBody HorarioDto horario) {

        Horario horarioSave = horarioService.horarioDtoToHorario(horario);
        System.out.println(horario.toString());
        System.out.println(horarioSave.toString());
        horarioSave.setId(-1L);
        horarioSave = horarioService.save(horarioSave);
        if(horarioSave == null){
            HorarioDto horarioRetorno = new HorarioDto();
            horarioRetorno.setConductorHorario("");
            return horarioRetorno;
        }
        HorarioDto horarioRetorno = horarioService.horarioToHorarioDto(horarioSave);
        return horarioRetorno;


    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/save")
    public String guardarHorario(@Valid Horario horario, BindingResult result, Model model) {
        model.addAttribute("conductores", conductorService.listarConductores());
        model.addAttribute("buses", busService.listarBuses());
        model.addAttribute("rutas", rutaService.listarRutas());
        model.addAttribute("diasSemana", DiasSemana.values());
        return "horario-add";
    }

}
