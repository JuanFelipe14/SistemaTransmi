package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.Enumeration.DiasSemana;
import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.model.Horario;
import com.example.sistematransmilenio.model.Ruta;
import com.example.sistematransmilenio.model.dto.HorarioDto;
import com.example.sistematransmilenio.service.BusService;
import com.example.sistematransmilenio.service.ConductorService;
import com.example.sistematransmilenio.service.HorarioService;
import com.example.sistematransmilenio.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/horario")
public class HorarioController{

    @Autowired
    HorarioService horarioService;
    @Autowired
    ConductorService conductorService;
    @Autowired
    BusService busService;
    @Autowired
    RutaService rutaService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<HorarioDto> listarHorarios() {
        List<HorarioDto> horarios = horarioService.listHorariosDto();
        //model.addAttribute("horarios", horarios);
        return horarios;
    }

    @GetMapping( "/save")
    public String guardarHorario(@Valid Horario horario, BindingResult result, Model model) {
        model.addAttribute("conductores",conductorService.listarConductores());
        model.addAttribute("buses",busService.listarBuses());
        model.addAttribute("rutas",rutaService.listarRutas());
        model.addAttribute("diasSemana", DiasSemana.values());
        return "horario-add";
    }

    /* TODO
    @PostMapping("/procesarHorarioCreado")
    public String procesarFormulario(@RequestParam("dropConducs") String dropConducs,@RequestParam("dropBuses") String dropBuses,@RequestParam("dropRutas") String dropRutas,@RequestParam("dropDias") String dropDias,@RequestParam("horaInicio") String horaInicio,@RequestParam("horaFin") String horaFin, Model model) {
        Horario horario = new Horario();
        System.out.println("DROP____:"+dropConducs);
        horario.setDiasSemana(DiasSemana.valueOf(dropDias));
        horario.setConductorHorario(conductorService.findConductorByCedula(Integer.valueOf(dropConducs)));
        horario.setRutaHorario(rutaService.findRutaByNombreRuta(dropRutas));
        horario.setBusHorario(busService.findBusByPlaca(dropBuses));
        horario.setHoraInicioStr(horaInicio);
        horario.setHoraFinStr(horaFin);
        busService.findBusByPlaca(dropBuses).getHorarioBus().add(horario);
        busService.guardarBus(busService.findBusByPlaca(dropBuses));
        conductorService.findConductorByCedula(Integer.valueOf(dropConducs)).getHorarioConductores().add(horario);
        conductorService.guardarConductor(conductorService.findConductorByCedula(Integer.valueOf(dropConducs)));
        rutaService.findRutaByNombreRuta(dropRutas).getHorarioRuta().add(horario);
        rutaService.findRutaByNombreRuta(dropRutas).setId(new Random(1000).nextLong());
        rutaService.guardarRuta(rutaService.findRutaByNombreRuta(dropRutas));

        horarioService.guardarHorario(horario);
        return "Bienvenida";
    }

    /*
@PostMapping("/procesarHorarioCreado")
@Transactional
public String procesarFormulario(@ModelAttribute("conductores") Conductor dropConducs, @ModelAttribute("buses") Bus dropBuses, @ModelAttribute("rutas") Ruta dropRutas, @ModelAttribute("dropDias")  String dropDias, @RequestParam("horaInicio") String horaInicio, @RequestParam("horaFin") String horaFin, Model model,HttpServletRequest request) {
    Horario horario = new Horario();
    String selectedValue = request.getParameter("mySelect");
    System.out.println("DROP:"+dropBuses);
    horario.setDiasSemana(DiasSemana.valueOf(dropDias));
    horario.setConductorHorario(dropConducs);
    horario.setRutaHorario(dropRutas);
    horario.setBusHorario(dropBuses);
    horario.setHoraInicioStr(horaInicio);
    horario.setHoraFinStr(horaFin);
    dropBuses.getHorarioBus().add(horario);
    busService.guardarBus(dropBuses);
    dropConducs.getHorarioConductores().add(horario);
    conductorService.guardarConductor(dropConducs);
    dropRutas.getHorarioRuta().add(horario);
    dropRutas.setId(new Random(1000).nextLong());
    rutaService.guardarRuta(dropRutas);

    horarioService.guardarHorario(horario);
    return "Bienvenida";
}*/

}
