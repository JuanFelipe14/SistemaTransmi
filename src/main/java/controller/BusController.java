package controller;

import model.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BusService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {
    private ArrayList<Bus> buses;

    private BusService busService;

    @GetMapping("/list")
    public String listarPersonas(Model model) {
        List<Bus> persons = busService.listarBuses();
        model.addAttribute("persons", persons);
        return "person-list";
    }



}
