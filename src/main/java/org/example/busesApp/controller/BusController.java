package org.example.busesApp.controller;

import org.example.busesApp.model.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.busesApp.service.BusService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {
    private ArrayList<Bus> buses;

    private BusService busService;

    @GetMapping("/list")
    public String listarPersonas(Model model) {
        List<Bus> buses = busService.listarBuses();
        model.addAttribute("buses", buses);
        return "listar-buses";
    }





}
