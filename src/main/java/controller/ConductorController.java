package controller;

import model.Conductor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/conductor")
public class ConductorController {
    private ArrayList<Conductor> conductores;
}
