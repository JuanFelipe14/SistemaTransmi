package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/conductor")
public class ConductorController {
    private ArrayList<Conductor> conductores;
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/list")
    public String listarConductores(Model model) {
        List<Conductor> conductor = conductorService.listarConductores();
        model.addAttribute("conductor", conductor);
        return "conductor-list";
    }

    @PostMapping(value = "/save")
    public String guardarConductor(@Valid Conductor conductor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CrearConductor";
        }
        conductorService.guardarConductor(conductor);
        return "redirect:/conductor/list";
    }

    @GetMapping("/view/{idConductor}")
    String verConductor(Model model, @PathVariable("idConductor") Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        model.addAttribute("conductor", conductor);
        return "conductor-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarConductor(Model model, @PathVariable Long id) {
        Conductor c = conductorService.recuperarConductor(id);
        model.addAttribute("conductor", c);
        return "conductor-edit";
    }

    @GetMapping("/search")
    public String listConductores(@RequestParam(required = false) String searchText, Model model) {
        List<Conductor> conductors;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            conductors = conductorService.listarConductores();
        } else {
            log.info("Buscando conductores cuyo nombre comienza con {}", searchText);
            conductors = conductorService.buscarPorNombre(searchText);
        }
        model.addAttribute("conductor", conductors);
        return "conductor-search";
    }


    @GetMapping(value = "/add")
    public String agregarConductor(Model model) {
        Conductor c= new Conductor();
        model.addAttribute("conductor", c);
        return "conductor-add";
    }

    @GetMapping(value = "/delete/{id}")
    public String eliminarConductor(@PathVariable Long id) {
        conductorService.eliminarConductor(id);
        return "redirect:/conductor/list";
    }

}
