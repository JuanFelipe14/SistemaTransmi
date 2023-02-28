package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.BusRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    public Iterable<Bus> listarBuses (){return busRepository.findAll();}
}

