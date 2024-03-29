package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.model.dto.BusDto;
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

@RestController
@RequestMapping("/bus")
public class BusController {
    private ArrayList<Bus> buses;

    @Autowired
    private BusService busService;

    Logger log = LoggerFactory.getLogger(getClass());

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<Bus> listarBuses() {
        //Iterable<Bus> bus = busService.listarBuses();
        //model.addAttribute("bus", bus);
        return busService.listarBuses();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/save")
    public String guardarBus(@Valid Bus bus, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CrearBus";
        }
        busService.guardarBus(bus);
        return "redirect:/bus/list";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/view/{idBus}")
    public Bus verBus(Model model, @PathVariable("idBus") Long id) {
        Bus bus = busService.recuperarBus(id);
        return bus;
    }

    @CrossOrigin("http://localhost:4200/")
    @PutMapping("/edit")
    public BusDto modificarBus(@Valid @RequestBody BusDto bus){
        Bus busAnterior = busService.findBusById(bus.getId());
        busAnterior.setModelo(bus.getModelo());
        busAnterior.setPlaca(bus.getPlaca());
        Bus busSave = busService.update(busAnterior);
        BusDto busRetorno = new BusDto(busSave.getId(),busSave.getPlaca(),busSave.getModelo());
        return busRetorno;
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


    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/add")
    public BusDto agregarBus(@Valid @RequestBody BusDto bus) {
        Bus nuevoBus = new Bus();
        nuevoBus.setPlaca(bus.getPlaca());
        nuevoBus.setModelo(bus.getModelo());
        nuevoBus = busService.save(nuevoBus);
        BusDto busRetorno = new BusDto(nuevoBus.getId(),nuevoBus.getPlaca(),nuevoBus.getModelo());
        return busRetorno;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/delete/{id}")
    public boolean eliminarBus(@PathVariable Long id) {
        return busService.eliminarBus(id);
    }

    
}
