package org.example.busesApp.service;

import org.example.busesApp.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.busesApp.repository.BusRepository;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> listarBuses (){return busRepository.findAll();}
}

