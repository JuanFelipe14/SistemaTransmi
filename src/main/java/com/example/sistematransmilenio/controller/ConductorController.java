package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.model.dto.ConductorDto;
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

@RestController
@RequestMapping("/conductor")
public class ConductorController {
    private ArrayList<Conductor> conductores;
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConductorService conductorService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<Conductor> listarConductores() {
        List<Conductor> conductor = conductorService.listarConductores();
        return conductor;
    }

    @PostMapping(value = "/save")
    public String guardarConductor(@Valid Conductor conductor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CrearConductor";
        }
        conductorService.guardarConductor(conductor);
        return "redirect:/conductor/list";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/view/{idConductor}")
    public ConductorDto verConductor( @PathVariable("idConductor") Long id) {
        return conductorService.conductorToConductorDto(conductorService.recuperarConductor(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit")
    public ConductorDto modificarConductor(@Valid @RequestBody ConductorDto conductor) {
        Conductor conductorAnterior = (Conductor) conductorService.findById(conductor.getId());
        conductorAnterior.setNombre(conductor.getNombre());
        conductorAnterior.setCedula(conductor.getCedula());
        conductorAnterior.setTelefono(conductor.getTelefono());
        conductorAnterior.setDireccion(conductor.getDireccion());
        Conductor conductorSave = conductorService.guardarConductor(conductorAnterior);
        ConductorDto conductorRetorno = new ConductorDto(conductorSave.getId(),conductorSave.getNombre(),conductorSave.getCedula(),conductorSave.getTelefono(),conductorSave.getDireccion());
        return conductorRetorno;
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


    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/add")
    public ConductorDto agregarConductor(@Valid @RequestBody ConductorDto conductor) {
        Conductor conductorNuevo = new Conductor();
        conductorNuevo.setNombre(conductor.getNombre());
        conductorNuevo.setCedula(conductor.getCedula());
        conductorNuevo.setTelefono(conductor.getTelefono());
        conductorNuevo.setDireccion(conductor.getDireccion());
        conductorNuevo = conductorService.guardarConductor(conductorNuevo);
        ConductorDto conductorRetorno = new ConductorDto(conductorNuevo.getId(),conductorNuevo.getNombre(),conductorNuevo.getCedula(),conductorNuevo.getTelefono(),conductorNuevo.getDireccion());
        return conductorRetorno;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping( "/delete/{id}")
    public boolean eliminarConductor(@PathVariable Long id) {
        try{
            conductorService.eliminarConductor(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
