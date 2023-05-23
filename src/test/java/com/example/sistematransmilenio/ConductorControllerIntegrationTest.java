package com.example.sistematransmilenio;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.repository.ConductorRepository;
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
public class ConductorControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    ConductorRepository conductorRepository;

    @Autowired
    private TestRestTemplate rest;



    @BeforeEach
    void init() {
        conductorRepository.save(new Conductor("Wil", 102868, 3005609505L,"TV 74"));
        conductorRepository.save(new Conductor("Pau", 293800, 3155636913L,"Cal 44"));
        conductorRepository.save(new Conductor("Pipe", 109273, 3183353070L,"TV 56"));
    }


    @Test
    void eliminarConductor()  throws Exception {
        this.rest.delete("http://localhost:" + this.port + "/conductor/delete/1", new Object[0]);
        Assertions.assertNull(((Conductor)this.rest.getForObject("http://localhost:" + this.port + "/conductor/delete/1", Conductor.class, new Object[0])).getNombre());
    }
    @Test
    void agregarConductor() {
        Conductor conductor = (Conductor)this.rest.postForObject("http://localhost:" + this.port + "/conductor/add", new Conductor( "Sof", 238900, 3004508707L,"Cal 89"), Conductor.class, new Object[0]);
        Assertions.assertEquals("Sof", conductor.getNombre());
    }


    @Test
    void modificarConductor() {
        Conductor conductor = (Conductor) this.rest.getForObject("http://localhost:" + this.port + "/conductor/view/2", Conductor.class, new Object[0]);
        conductor.setNombre("Juan");
        conductor.setCedula(109275);
        conductor.setTelefono(3183353070L);
        this.rest.put("http://localhost:" + this.port + "/conductor/edit/2", conductor, Conductor.class);
        System.out.println(conductor.getNombre());
        Assertions.assertEquals(new String("Juan"), conductor.getNombre());
    }

    @Test
    void verConductor() {
        Conductor conductor = (Conductor) this.rest.getForObject("http://localhost:" + this.port + "/conductor/view/2", Conductor.class, new Object[0]);
        System.out.println(conductor.getNombre());
        Assertions.assertEquals(new String("Pau"), conductor.getNombre());
    }
}
