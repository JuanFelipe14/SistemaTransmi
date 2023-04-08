package com.example.sistematransmilenio.Database;

import com.example.sistematransmilenio.Enumeration.DiasSemana;
import com.example.sistematransmilenio.model.*;
import com.example.sistematransmilenio.repository.*;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
public class DataBaseInit implements ApplicationRunner {

    private static final int NUM_CONDUCTORES = 50;
    private static final int NUM_BUSES = 50;
    private static final int NUM_RUTAS = 50;

    @Autowired
    ConductorRepository conductorRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    EstacionRepository estacionRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        /*
        Random random = new Random(1234);

        RandomStringGenerator randomGen = new RandomStringGenerator.Builder().withinRange('a', 'z').usingRandom(random::nextInt).build();
        String nombre1 = "Sebs";
        int cedula1 = 123421;
        long telefono1 =3005609505l;
        String direccion1 = "TV 74";
        conductorRepository.save(new Conductor(nombre1, cedula1, telefono1, direccion1));


        //Crear conductores
        for (int i = 0; i < NUM_CONDUCTORES; i++) {
            String nombre = randomGen.generate(5, 10);
            int cedula = random.nextInt(1000000000);
            int telefono = random.nextInt(1000000000);
            String direccion = randomGen.generate(5, 5);
            conductorRepository.save(new Conductor(nombre, cedula, telefono, direccion));
        }
        String placa1 ="FUD660";
        String modelo1 ="VOLVO";
        busRepository.save(new Bus(placa1,modelo1));
        //Crear buses
        for (int i = 0; i < NUM_BUSES; i++) {
            String placa = randomGen.generate(5, 10);
            String modelo = randomGen.generate(5, 10);

            busRepository.save(new Bus(placa, modelo));
        }


        rutaRepository.save(new Ruta("H23",null));

        Estacion estacion = new Estacion("Tunal");
        Estacion estacion2 = new Estacion("Prado");
        Estacion estacion3 = new Estacion("85");
        Estacion estacion4 = new Estacion("45");

        Ruta ruta = new Ruta();
        ruta.setNombreRuta("Ruta 1");
        ArrayList<Ruta> rutas = new ArrayList<>();

        ArrayList<Estacion> estaciones = new ArrayList<>();
        estaciones.add(estacion);
        estacionRepository.save(estacion);
        estaciones.add(estacion2);
        estacionRepository.save(estacion2);
        estaciones.add(estacion3);
        estacionRepository.save(estacion3);
        estaciones.add(estacion4);
        estacionRepository.save(estacion4);
        ruta.setEstaciones(estaciones);
        ruta.getEstaciones().get(0).setRutas(rutas);
        ruta.getEstaciones().get(1).setRutas(rutas);
        ruta.getEstaciones().get(2).setRutas(rutas);
        ruta.getEstaciones().get(3).setRutas(rutas);


        ruta.setId(new Random(1000).nextLong());

        rutaRepository.save(ruta);



        Horario horario = new Horario();
        horario.setBusHorario(busRepository.findAll().get(0));
        horario.setRutaHorario(ruta);
        horario.setConductorHorario(conductorRepository.findAll().get(0));
        horario.setId(new Random(1).nextLong());
        Calendar cal = Calendar.getInstance();
        cal.set(0,0,0,0,0,0);

        cal.set(Calendar.HOUR_OF_DAY, 8);

        Date dateInicio = cal.getTime();
        cal.set(0,0,0,0,0,0);
        cal.set(Calendar.HOUR_OF_DAY, 16);
        Date dateFin = cal.getTime();
        horario.setHoraInicio(dateInicio);
        horario.setHoraFin(dateFin);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horario.getHoraFin());
        horario.setHoraFinStr(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        calendar.setTime(horario.getHoraInicio());
        horario.setHoraInicioStr(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        horario.setDiasSemana(DiasSemana.JUEVES);
        ArrayList<Horario> horarios = new ArrayList<>();
        horario.setRutaHorario(null);
        horarios.add(horario);

        ruta.setHorarioRuta(horarios);

        horarioRepository.save(horario);
*/
    }
}
