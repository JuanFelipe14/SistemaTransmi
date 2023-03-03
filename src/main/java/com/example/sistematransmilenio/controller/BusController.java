package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.sistematransmilenio.service.BusService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {
    private ArrayList<Bus> buses;

    @Autowired
    private BusService busService;

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/list")
    public String listarBuses(Model model) {
        Iterable<Bus> bus = busService.listarBuses();
        model.addAttribute("bus", bus);
        return "bus-list";
    }

    @PostMapping(value = "/save")
    public String guardarBus(@Valid Bus bus, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CrearBus";
        }
        busService.guardarBus(bus);
        return "redirect:/bus/list";
    }

    @GetMapping("/view/{idBus}")
    String verBus(Model model, @PathVariable("idBus") Long id) {
        Bus bus = busService.recuperarBus(id);
        model.addAttribute("bus", bus);
        return "bus-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarBus(Model model, @PathVariable Long id) {
        Bus b = busService.recuperarBus(id);
        model.addAttribute("bus", b);
        return "bus-edit";
    }

    @GetMapping("/search")
    public String listBuses(@RequestParam(required = false) String searchText, Model model) {
        List<Bus> bus;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            bus = busService.listarBuses();
        } else {
            log.info("Buscando conductores cuyo nombre comienza con {}", searchText);
            bus = busService.buscarPorNombre(searchText);
        }
        model.addAttribute("bus", bus);
        return "bus-search";
    }


    @GetMapping(value = "/add")
    public String agregarBus(Model model) {
        Bus b= new Bus();
        model.addAttribute("bus", b);
        return "bus-add";
    }

    @GetMapping(value = "/delete/{id}")
    public String eliminarConductor(@PathVariable Long id) {
        busService.eliminarBus(id);
        return "redirect:/bus/list";
    }




}
