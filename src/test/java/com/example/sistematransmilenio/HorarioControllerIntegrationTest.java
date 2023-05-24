package com.example.sistematransmilenio;

import com.example.sistematransmilenio.Enumeration.DiasSemana;
import com.example.sistematransmilenio.model.*;
import com.example.sistematransmilenio.model.dto.ConductorDto;
import com.example.sistematransmilenio.model.dto.HorarioDto;
import com.example.sistematransmilenio.model.dto.RutaDto;
import com.example.sistematransmilenio.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;


@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HorarioControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    HorarioRepository horarioRepository;
    @Autowired
    BusRepository busRepository;
    @Autowired
    ConductorRepository conductorRepository;
    @Autowired
    RutaRepository rutaRepository;
    @Autowired
    EstacionRepository estacionRepository;


    @Autowired
    private TestRestTemplate rest;



    @BeforeEach
    void init() {
        Estacion estacion = new Estacion("Tunal");
        Estacion estacion2 = new Estacion("Prado");
        Estacion estacion3 = new Estacion("85");
        Estacion estacion4 = new Estacion("45");

        Ruta ruta = new Ruta();
        ruta.setNombreRuta("Ruta1");
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


        rutaRepository.save(ruta);

        // System.out.println(ruta.toString());

        String placa1 ="FUD660";
        String modelo1 ="VOLVO";
        Bus bus= new Bus(placa1,modelo1);
        busRepository.save(bus);
        String nombre1 = "Sebs";
        int cedula1 = 123421;
        long telefono1 =3005609505l;
        String direccion1 = "TV 74";
        conductorRepository.save(new Conductor(nombre1, cedula1, telefono1, direccion1));

        Horario horario = new Horario();
        horario.setBusHorario(busRepository.findAll().get(0));
        horario.setRutaHorario(ruta);
        horario.setConductorHorario(conductorRepository.findAll().get(0));
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
        horario.setRutaHorario(ruta);
        horarios.add(horario);

        ruta.setHorarioRuta(horarios);


        horarioRepository.save(horario);

        // System.out.println(horario.toString());

        /*horarioRepository.save(new Horario(new Bus("KMN234","MERCEDES"),new Conductor("pau",67898,3005609505L,"Cal 44"),new Ruta("Ruta2",estaciones), DiasSemana.MIERCOLES,"8","12"));

        horarioRepository.save(new Horario(new Bus("SWN678","VOLVO"),new Conductor("wil",13908,3183353070L,"Tr 80"),new Ruta("Ruta3",estaciones), DiasSemana.DOMINGO,"9","13"));
         */
    }



    @Test
    void eliminarHorario() throws Exception {
        this.rest.delete("http://localhost:" + this.port + "/horario/delete/1", new Object[0]);
        Assertions.assertNull(((Horario)this.rest.getForObject("http://localhost:" + this.port + "/horario/delete/1", Horario.class, new Object[0])).getHoraInicioStr());
    }
    @Test
    void agregarHorario() {

        System.out.println((List<RutaDto>)this.rest.getForObject("http://localhost:" + this.port + "/ruta/list",List.class,new Object[0]));
        HorarioDto horario= new HorarioDto();
        horario.setBusHorario("FUD660");
        horario.setConductorHorario("Sebs");
        horario.setIdConductorHorario(7L);
        horario.setIdRutaHorario(5L);
        // horario.getRutaHorario("Ruta1");
        horario.setDiasSemana(DiasSemana.MIERCOLES);
        horario.setHoraInicioStr("8");
        horario.setHoraFinStr("18");
        horario =(HorarioDto)this.rest.postForObject("http://localhost:" + this.port + "/horario/add",horario, HorarioDto.class);
        //System.out.println((List<HorarioDto>)this.rest.getForObject("http://localhost:" + this.port + "/horario/list",List.class,new Object[0]));
        Assertions.assertEquals( "FUD660",horario.getBusHorario());
    }

    @Test
    void modificarHorario() {
        ArrayList<Estacion> estaciones= new ArrayList<>();
        HorarioDto horario = (HorarioDto)this.rest.getForObject("http://localhost:" + this.port + "/horario/view/2", HorarioDto.class, new Object[0]);
        horario.setDiasSemana(DiasSemana.MIERCOLES);
        horario.setHoraFinStr("15");
        this.rest.put("http://localhost:" + this.port + "/horario/edit",horario, Horario.class);
        System.out.println(horario.getHoraFinStr());
        Assertions.assertEquals(new String("15"), horario.getHoraFinStr());
    }

    @Test
    void verHorario() {
        HorarioDto horario = (HorarioDto)this.rest.getForObject("http://localhost:" + this.port + "/horario/view/8", HorarioDto.class, new Object[0]);
        // System.out.println((List<HorarioDto>)this.rest.getForObject("http://localhost:" + this.port + "/horario/list",List.class,new Object[0]));
        System.out.println(horario);
        Assertions.assertEquals(new String("16"), horario.getHoraFinStr());
    }
}
