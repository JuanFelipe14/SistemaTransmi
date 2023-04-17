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

import javax.validation.Valid;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/delete/{id}")
    public boolean eliminarRutar(@PathVariable Long id) {
        return rutaService.deleteRuta(id);
    }

    @CrossOrigin("http://localhost:4200/")
    @PostMapping(value = "/add")
    public RutaDto agregarEstacion(@Valid @RequestBody RutaDto rutaDto) {
        Ruta rutaNueva = new Ruta();
        rutaNueva = rutaService.rutaDtoToRuta(rutaDto);
        rutaNueva = rutaService.save(rutaNueva);
        RutaDto rutaRetorno = rutaService.rutaToRutaDto(rutaNueva);
        return rutaRetorno;
    }

    @CrossOrigin("http://localhost:4200/")
    @PutMapping("/edit")
    public RutaDto modificarRuta(@Valid @RequestBody RutaDto ruta) {
        //Ruta rutaAnterior = rutaService.findById(ruta.getId());
        System.out.println(ruta.toString());
        Ruta rutaAux = rutaService.rutaDtoToRuta(ruta);
        System.out.println(rutaAux.toString());

        //rutaAnterior.setEstaciones(rutaAux.getEstaciones());
        Ruta rutaSave=rutaService.update(rutaAux);
        RutaDto rutaRetorno = rutaService.rutaToRutaDto(rutaSave);
        return  rutaRetorno;
    }



}
