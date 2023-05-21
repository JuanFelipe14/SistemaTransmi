package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.dto.EstacionDto;
import com.example.sistematransmilenio.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estacion")
public class EstacionController {

    @Autowired
    EstacionService estacionService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<EstacionDto> listarEstaciones(){
        return estacionService.listEstacionesDto();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public boolean eliminarEstacion(@PathVariable Long id) {
        return estacionService.eliminarEstacion(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/view/{idEstacion}")
    public EstacionDto findEstacionById(Model model, @PathVariable("idEstacion") Long id) {
        Estacion estacion = estacionService.findEstacionById(id);
        EstacionDto estacionEnvio =  new EstacionDto(estacion.getId(),estacion.getNombre(),estacion.getMapKeyNumber());
        return estacionEnvio;
    }

    @CrossOrigin("http://localhost:4200/")
    @PutMapping("/edit")
    public EstacionDto modificarEstacion(@Valid @RequestBody EstacionDto estacion) {
        Estacion estacionAnterior = estacionService.findEstacionById(estacion.getId());
        estacionAnterior.setNombre(estacion.getNombre());
        estacionAnterior.setMapKeyNumber(estacion.getMapKey());
        Estacion estacionSave=estacionService.update(estacionAnterior);
        EstacionDto estacionRetorno = new EstacionDto(estacionSave.getId(), estacionSave.getNombre(),estacionSave.getMapKeyNumber());
        return  estacionRetorno;
    }

    @CrossOrigin("http://localhost:4200/")
    @PostMapping(value = "/add")
    public EstacionDto agregarEstacion(@Valid @RequestBody EstacionDto estacion) {
        Estacion estacionNueva = new Estacion();
        estacionNueva.setNombre(estacion.getNombre());
        estacionNueva.setMapKeyNumber(estacion.getMapKey());
        estacionNueva = estacionService.save(estacionNueva);
        EstacionDto estacionRetorno = new EstacionDto(estacionNueva.getId(),estacionNueva.getNombre(),estacionNueva.getMapKeyNumber());
        return estacionRetorno;
    }




}
