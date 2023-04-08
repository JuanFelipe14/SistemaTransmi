package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.dto.EstacionDto;
import com.example.sistematransmilenio.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
