package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.repository.EstacionRepository;
import com.example.sistematransmilenio.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstacionService {
    @Autowired
    private EstacionRepository estacionRepository;

}
