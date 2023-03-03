package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.model.Horario;
import com.example.sistematransmilenio.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    HorarioService horarioService;
    @GetMapping("/list")
    public String listarHorarios(Model model) {
        List<Horario> horarios = horarioService.listHorarios();
        model.addAttribute("horarios", horarios);
        return "Bienvenida";
    }

    @PostMapping(value = "/save")
    public String guardarHorario(@Valid Horario horario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CrearBus";
        }
        horarioService.guardarHorario(horario);
        return "redirect:/bus/list";
    }

}
