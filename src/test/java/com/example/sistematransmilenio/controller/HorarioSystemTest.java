package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.Enumeration.DiasSemana;
import com.example.sistematransmilenio.model.*;
import com.example.sistematransmilenio.model.dto.HorarioDto;
import com.example.sistematransmilenio.repository.ConductorRepository;
import com.example.sistematransmilenio.repository.HorarioRepository;
import com.example.sistematransmilenio.repository.BusRepository;
import com.example.sistematransmilenio.repository.RutaRepository;
import com.example.sistematransmilenio.repository.EstacionRepository;


import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ActiveProfiles("systemtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HorarioSystemTest {

    private ChromeDriver browser;
    private WebDriverWait wait;

    private int timeout = 50;

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

    String baseUrl;

    @BeforeEach
    void init() {

        Bus busTest = new Bus("HSA123", "2003");
        busRepository.save(busTest);
        Conductor conductorTest = new Conductor("Sofia", 1000123456, 123456789, "Javeriana");
        conductorRepository.save(conductorTest);
        ArrayList<Estacion> estaciones = new ArrayList<>();

        Estacion estacionTest = new Estacion("Alcala");
        Estacion estacionTest1 = new Estacion("Suba");

        estacionRepository.save(estacionTest);
        estacionRepository.save(estacionTest1);

        estaciones.add(estacionTest);
        estaciones.add(estacionTest1);

        Ruta rutaTest = new Ruta("Caracas", estaciones);
        rutaRepository.save(rutaTest);
        String horaInicioStrTest = "13:10";
        String horaFinStrTest = "15:30";

        Horario horario = new Horario();

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
        horario.setDiasSemana(DiasSemana.MARTES);

        horario.setBusHorario(busTest);
        horario.setConductorHorario(conductorTest);
        horario.setRutaHorario(rutaTest);

        horarioRepository.save(horario);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.merge(DesiredCapabilities.chrome());

        this.browser = new ChromeDriver(options);
        this.wait = new WebDriverWait(browser, timeout);

        this.baseUrl = "http://localhost:4200";
    }

    @AfterEach
    void end() {
        this.browser.quit();
    }

    @Test
    void obtenerConductor() {
        List<Horario> horarios = horarioRepository.findAll();
        browser.get(baseUrl + "/horario/view/6");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("conductorHorarioId")));
        WebElement conductorHorarioId = browser.findElement(By.id("conductorHorarioId"));
        Assert.assertEquals("Conductor actual: " + "Sofia", conductorHorarioId.getText());
    }

    @Test
    void obtenerBus() {
        browser.get(baseUrl + "/horario/view/6");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("busHorarioId")));
        WebElement busHorarioId = browser.findElement(By.id("busHorarioId"));
        Assert.assertEquals("Bus actual: HSA123", busHorarioId.getText());
    }

    @Test
    void obtenerDia() {
        browser.get(baseUrl + "/horario/view/6");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("diaHorarioId")));
        WebElement diaHorarioId = browser.findElement(By.id("diaHorarioId"));
        Assert.assertEquals("Día actual: MARTES", diaHorarioId.getText());
    }

    @Test
    void obtenerHoraInicio() {
        browser.get(baseUrl + "/horario/view/6");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("horaInicioHorarioId")));
        WebElement horaInicioHorarioId = browser.findElement(By.id("horaInicioHorarioId"));
        Assert.assertEquals("Hora Inicio actual: 8", horaInicioHorarioId.getText());
    }


    @Test
    void obtenerHoraFinal() {
        browser.get(baseUrl + "/horario/view/6");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("horaFinalHorarioId")));
        WebElement horaFinalHorarioId = browser.findElement(By.id("horaFinalHorarioId"));
        Assert.assertEquals("Hora final actual: 16", horaFinalHorarioId.getText());
    }


    @Test
    void obtenerRuta() {
        browser.get(baseUrl + "/horario/view/6");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rutaHorarioId")));
        WebElement rutaHorarioId = browser.findElement(By.id("rutaHorarioId"));
        Assert.assertEquals("Ruta actual: Caracas", rutaHorarioId.getText());
    }
}