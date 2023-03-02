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

@Controller
@RequestMapping("/conductor")
public class ConductorController {
    private ArrayList<Conductor> conductores;
    @Autowired
    private ConductorService conductorService;

    @GetMapping("/list")
    public String listarConductores(Model model) {
        List<Conductor> conductores = conductorService.listarConductores();
        model.addAttribute("conductores", conductores);
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
    String verPersona(Model model, @PathVariable("idConductor") Long id) {
        Conductor conductor = conductorService.recuperarConductor(id);
        model.addAttribute("conductor", conductor);
        return "conductor-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarPersona(Model model, @PathVariable Long id) {
        Conductor c = conductorService.recuperarConductor(id);
        model.addAttribute("conductor", c);
        return "person-edit";
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
        model.addAttribute("conductores", conductors);
        return "conductor-search";
    }

    @GetMapping(value = "/agregar")
    public String agregarConductor(Model model) {
        model.addAttribute("conductor", new Conductor());
        return "productos/agregar_producto";
    }

    /*@PostMapping(value = "/eliminar")
    public String eliminarConductor(@ModelAttribute Conductor conductor, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productosRepository.deleteById(producto.getId());
        return "redirect:/productos/mostrar";
    }*/

}
