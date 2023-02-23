package service;

import model.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RutaRepository;

import java.util.List;

@Service
public class RutaService {

    @Autowired
    RutaRepository rutaRepository;

    public List<Ruta> listarRutas (){return rutaRepository.findAll();}
}
