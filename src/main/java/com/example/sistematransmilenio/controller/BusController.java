package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.sistematransmilenio.service.BusService;

import java.util.ArrayList;

@Controller
@RequestMapping("/bus")
public class BusController {
    private ArrayList<Bus> buses;

    private BusService busService;

    @GetMapping("/list")
    public String listarPersonas(Model model) {
        Iterable<Bus> persons = busService.listarBuses();
        model.addAttribute("persons", persons);
        return "person-list";
    }



}
