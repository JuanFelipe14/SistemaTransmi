package com.example.sistematransmilenio.service;

import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.Horario;
import com.example.sistematransmilenio.model.Ruta;
import com.example.sistematransmilenio.model.dto.EstacionDto;
import com.example.sistematransmilenio.model.dto.RutaDto;
import com.example.sistematransmilenio.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistematransmilenio.repository.RutaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutaService {

    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    HorarioRepository horarioRepository;
    @Autowired
    EstacionService estacionService;
    public List<Ruta> listarRutas (){return rutaRepository.findAll();}

    public Ruta findRutaByNombreRuta(String nombreRuta){return rutaRepository.findRutaByNombreRuta(nombreRuta);}

    public void guardarRuta(Ruta ruta){rutaRepository.save(ruta);}

    public RutaDto rutaToRutaDto(Ruta ruta){
        List<String> estaciones = new ArrayList<>();
        for(Estacion estacion: ruta.getEstaciones()){
            String nuevaEstacion = new EstacionDto(estacion.getId(),estacion.getNombre(), estacion.getMapKeyNumber()).getNombre();
            estaciones.add(nuevaEstacion);
        }
        return new RutaDto(ruta.getId(),ruta.getNombreRuta(),estaciones);
    }

    public List<RutaDto> allRutasToRutasDto() {
        List<Ruta> rutas = this.listarRutas();
        List<RutaDto> rutasDtoRetorno = new ArrayList<>();
        for(Ruta ruta: rutas){
            rutasDtoRetorno.add(this.rutaToRutaDto(ruta));
        }
        return rutasDtoRetorno;
    }

    public RutaDto findRutaDtoById(Long id) {
        Ruta ruta = rutaRepository.findById(id).get();
        return this.rutaToRutaDto(ruta);
    }

    public boolean  deleteRuta(Long id) {
        this.rutaRepository.deleteById(id);
        return true;
    }

    public Ruta rutaDtoToRuta(RutaDto rutaDto) {
        Ruta rutaRetorno = new Ruta();
        rutaRetorno.setNombreRuta(rutaDto.getNombreRuta());
        for(String s: rutaDto.getEstaciones()){
            Estacion estacion = estacionService.findEstacionByNombre(s);
            rutaRetorno.getEstaciones().add(estacion);
        }
        rutaRetorno.setId(rutaDto.getId());
        return rutaRetorno;
    }

    public Ruta save(Ruta rutaNueva) {
        Estacion es1 = rutaNueva.getEstaciones().get(0);
        for(Estacion es: rutaNueva.getEstaciones()){
            Long dif= Math.abs(es1.getMapKeyNumber() - es.getMapKeyNumber());
            if(dif>1){
                return new Ruta();
            }
            es1 =es;
        }
        return this.rutaRepository.save(rutaNueva);
    }

    public Ruta findById(Long id) {
        return rutaRepository.findById(id).get();
    }

    public Ruta update(Ruta rutaAnterior) {
        return rutaRepository.save(rutaAnterior);
    }

    public boolean eliminarRuta(Long id) {
        if(this.validarRuta(id)){
            rutaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    private boolean validarRuta(Long id) {
        List<Horario> horarios = horarioRepository.getHorarioByRutaWithBus(id);
        if(horarios.isEmpty()){
            return true;
        }else{
            for(Horario h: horarios){
                System.out.println(h.toString());
            }

            return false;
        }
    }
}
