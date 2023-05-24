package com.example.sistematransmilenio;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.model.dto.RutaDto;
import com.example.sistematransmilenio.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("integrationtest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    BusRepository busRepository;

    @Autowired
    private TestRestTemplate rest;


    @BeforeEach
    void init() {
        busRepository.save(new Bus("FUD660", "VOLVO"));
        busRepository.save(new Bus("FSO288", "MERCEDES"));
        busRepository.save(new Bus("WKO513", "VOLVO"));
    }


    @Test
    void eliminarBus() throws Exception {
        System.out.println((List<Bus>)this.rest.getForObject("http://localhost:" + this.port + "/bus/list",List.class,new Object[0]));
        this.rest.delete("http://localhost:" + this.port + "/bus/delete/2",new Object[0]);
        System.out.println((List<Bus>)this.rest.getForObject("http://localhost:" + this.port + "/bus/list",List.class,new Object[0]));
        Assertions.assertNull(((Bus) this.rest.getForObject("http://localhost:" + this.port + "/bus/view/2",Bus.class)));
    }
    @Test
    void agregarBus() {
        Bus bus = (Bus)this.rest.postForObject("http://localhost:" + this.port + "/bus/add", new Bus( "SVO234", "Mercedes"), Bus.class, new Object[0]);
        Assertions.assertEquals("SVO234", bus.getPlaca());
    }

    @Test
    void modificarBus() {
        Bus bus = (Bus)this.rest.getForObject("http://localhost:" + this.port + "/bus/view/2", Bus.class, new Object[0]);
        bus.setPlaca("KJG822");
        bus.setModelo("VOLVO");
        this.rest.put("http://localhost:" + this.port + "/bus/edit",bus,Bus.class);
        System.out.println(bus.getPlaca());
        Assertions.assertEquals(new String("KJG822"), bus.getPlaca());
    }

    @Test
    void verBus() {
        Bus bus = (Bus)this.rest.getForObject("http://localhost:" + this.port + "/bus/view/1", Bus.class, new Object[0]);
        System.out.println(bus.getPlaca());
        Assertions.assertEquals(new String("FUD660"), bus.getPlaca());
    }

}
