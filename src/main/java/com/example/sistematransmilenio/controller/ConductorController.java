package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.service.ConductorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/conductor")
public class ConductorController {
    private ArrayList<Conductor> conductores;

    private ConductorService conductorService;

    @GetMapping("/list")
    public String listarConductores(Model model) {
        List<Conductor> conductores = conductorService.listarConductores();
        model.addAttribute("conductores", conductores);
        return "conductor-list";
    }

}
