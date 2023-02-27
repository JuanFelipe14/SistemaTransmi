package Database;

import model.Bus;
import model.Conductor;
import model.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.apache.commons.text.RandomStringGenerator;
import repository.BusRepository;
import repository.ConductorRepository;
import repository.HorarioRepository;
import repository.RutaRepository;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Random random = new Random(1234);

        RandomStringGenerator randomGen = new RandomStringGenerator.Builder().withinRange('a','z').usingRandom(random::nextInt).build();

        //Crear conductores
        for(int i = 0 ; i < NUM_CONDUCTORES ; i++){
            String nombre = randomGen.generate(5,10);
            int cedula = random.nextInt(1000000000);
            int telefono = random.nextInt(1000000000);
            String direccion = randomGen.generate(5,5);
            conductorRepository.save(new Conductor(nombre,cedula,telefono,direccion));
        }

        //Crear buses
        for(int i = 0 ; i < NUM_BUSES ; i++){
            String placa = randomGen.generate(5,10);
            String modelo = randomGen.generate(5,10);

            busRepository.save(new Bus(placa,modelo));
        }

        //Crear rutas
        for(int i = 0 ; i < NUM_RUTAS ; i++){
            int codigo = random.nextInt();
            ArrayList<String> estaciones = new ArrayList<>();
            String horarios = randomGen.generate(5,10);
            rutaRepository.save(new Ruta(codigo,estaciones,horarios));
        }

    }
}
