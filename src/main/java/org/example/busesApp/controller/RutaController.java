package org.example.busesApp.controller;

import org.example.busesApp.model.Ruta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/ruta")
public class RutaController {
    private ArrayList<Ruta> rutas;
}
