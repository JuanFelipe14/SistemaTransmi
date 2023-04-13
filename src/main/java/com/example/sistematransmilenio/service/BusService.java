package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.BusRepository;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    public List<Bus> listarBuses (){return busRepository.findAll();}

    public Bus recuperarBus(Long id) { return  busRepository.findById(id).get();
    }

    public Bus findBusByPlaca(String placa){return busRepository.findBusByPlaca(placa);}

    public void guardarBus(Bus bus) {
        busRepository.save(bus);
    }

    public List<Bus> buscarPorNombre(String textoBusqueda) {
        return busRepository.findBusByPlacaStartingWith(textoBusqueda);
    }

    public Bus save(Bus nuevoBus){
        return busRepository.save(nuevoBus);
    }

    public void eliminarBus(long id ){
        busRepository.deleteById(id);
    }
}

