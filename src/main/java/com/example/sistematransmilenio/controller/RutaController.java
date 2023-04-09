package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.Ruta;
import com.example.sistematransmilenio.model.dto.EstacionDto;
import com.example.sistematransmilenio.model.dto.RutaDto;
import com.example.sistematransmilenio.service.BusService;
import com.example.sistematransmilenio.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaService rutaService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<RutaDto> listarRutas() {
        return rutaService.allRutasToRutasDto();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/view/{idRuta}")
    public RutaDto verBus(@PathVariable("idRuta") Long id) {
        return rutaService.findRutaDtoById(id);
    }


}
