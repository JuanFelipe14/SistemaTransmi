package com.example.sistematransmilenio;


import com.example.sistematransmilenio.model.*;
import com.example.sistematransmilenio.model.dto.RutaDto;
import com.example.sistematransmilenio.repository.EstacionRepository;
import com.example.sistematransmilenio.repository.RutaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RutaControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    EstacionRepository estacionRepository;
    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    private TestRestTemplate rest;


    @BeforeEach
    void init() {
        Estacion estacion = new Estacion("Tunal");
        Estacion estacion2 = new Estacion("Prado");
        Estacion estacion3 = new Estacion("85");
        Estacion estacion4 = new Estacion("45");
        Ruta ruta = new Ruta();
        ruta.setNombreRuta("Ruta 1");

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

        rutaRepository.save(ruta);
        Estacion estacion5 = new Estacion("Militar");
        Estacion estacion6 = new Estacion("Prado");
        Estacion estacion7 = new Estacion("Portal");
        Estacion estacion8 = new Estacion("45");
        Ruta ruta2 = new Ruta();
        ruta.setNombreRuta("Ruta3");

        ArrayList<Estacion> estaciones2 = new ArrayList<>();
        estaciones2.add(estacion5);
        estacionRepository.save(estacion5);
        estaciones2.add(estacion6);
        estacionRepository.save(estacion6);
        estaciones2.add(estacion7);
        estacionRepository.save(estacion7);
        estaciones2.add(estacion8);
        estacionRepository.save(estacion8);
        ruta.setEstaciones(estaciones2);
        rutaRepository.save(ruta2);
        /*
        rutaRepository.save(new Ruta("Ruta1",estaciones));
        rutaRepository.save(new Ruta("Ruta2",estaciones));
        rutaRepository.save(new Ruta("Ruta3",estaciones));
         */
    }


    @Test
    void eliminarRuta() throws Exception {
        this.rest.delete("http://localhost:" + this.port + "/ruta/delete/1", new Object[0]);
        Assertions.assertNull(((Ruta)this.rest.getForObject("http://localhost:" + this.port + "/ruta/delete/1", Ruta.class, new Object[0])).getNombreRuta());
    }
    @Test
    void agregarRuta() {
        ArrayList<Estacion> estaciones= new ArrayList<>();
        Ruta ruta = (Ruta)this.rest.postForObject("http://localhost:" + this.port + "/ruta/add", new Ruta( "Ruta4", estaciones), Ruta.class, new Object[0]);
        Assertions.assertEquals("Ruta4", ruta.getNombreRuta());
    }

    @Test
    void modificarRuta() {
        ArrayList<Estacion> estaciones= new ArrayList<>();
        Ruta ruta = (Ruta)this.rest.getForObject("http://localhost:" + this.port + "/ruta/view/1", Ruta.class, new Object[0]);
        ruta.setNombreRuta("Ruta5");

        this.rest.put("http://localhost:" + this.port + "/ruta/edit",ruta, Ruta.class);
        System.out.println(ruta.getNombreRuta());
        Assertions.assertEquals(new String("Ruta5"), ruta.getNombreRuta());
    }

    @Test
    void verRuta() {
        RutaDto ruta = (RutaDto)this.rest.getForObject("http://localhost:" + this.port + "/ruta/view/5",RutaDto.class,new Object[0]);
        System.out.println((List<RutaDto>)this.rest.getForObject("http://localhost:" + this.port + "/ruta/list",List.class,new Object[0]));
        System.out.println(ruta.getNombreRuta());
        Assertions.assertEquals(new String("Ruta 1"), ruta.getNombreRuta());
    }
}
