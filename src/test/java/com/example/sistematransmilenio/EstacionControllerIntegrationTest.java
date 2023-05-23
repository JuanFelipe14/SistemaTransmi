package com.example.sistematransmilenio;

import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.repository.EstacionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstacionControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    EstacionRepository estacionRepository;

    @Autowired
    private TestRestTemplate rest;


    @BeforeEach
    void init() {
        estacionRepository.save(new Estacion("Tunal"));
        estacionRepository.save(new Estacion("Prado"));
        estacionRepository.save(new Estacion("Flores"));
    }


    @Test
    void eliminarEstacion() throws Exception {
        this.rest.delete("http://localhost:" + this.port + "/estacion/delete/1", new Object[0]);
        Assertions.assertNull(((Estacion)this.rest.getForObject("http://localhost:" + this.port + "/estacion/delete/1", Estacion.class, new Object[0])).getNombre());
    }
    @Test
    void agregarEstacion() {
        Estacion estacion = (Estacion)this.rest.postForObject("http://localhost:" + this.port + "/estacion/add", new Estacion( "Alcala"), Estacion.class, new Object[0]);
        Assertions.assertEquals("Alcala", estacion.getNombre());
    }


    @Test
    void modificarEstacion() {
        Estacion estacion = (Estacion)this.rest.getForObject("http://localhost:" + this.port + "/estacion/view/2", Estacion.class, new Object[0]);
        estacion.setNombre("Militar");

        this.rest.put("http://localhost:" + this.port + "/estacion/edit/2", estacion, Estacion.class);
        System.out.println(estacion.getNombre());
        Assertions.assertEquals(new String("Militar"), estacion.getNombre());
    }

    @Test
    void verEstacion() {
        Estacion estacion = (Estacion)this.rest.getForObject("http://localhost:" + this.port + "/estacion/view/3", Estacion.class, new Object[0]);
        System.out.println(estacion.getNombre());
        Assertions.assertEquals(new String("Flores"), estacion.getNombre());
    }
}
