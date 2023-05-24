package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Estacion;
import com.example.sistematransmilenio.model.Ruta;
import com.example.sistematransmilenio.model.dto.RutaDto;
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
import com.example.sistematransmilenio.repository.RutaRepository;
import com.example.sistematransmilenio.repository.EstacionRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("systemtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RutaSystemTest {

    private ChromeDriver browser;
    private WebDriverWait wait;

    private int timeout = 35;

    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    EstacionRepository estacionRepository;

    String baseUrl;

    @BeforeEach
    void init() {

        ArrayList<Estacion> estaciones = new ArrayList<>();
        Estacion estacionTest = new Estacion("Alcala");
        Estacion estacionTest1 = new Estacion("Suba");
        estaciones.add(estacionTest);
        estaciones.add(estacionTest1);

        estacionRepository.save(estacionTest);
        estacionRepository.save(estacionTest1);
        rutaRepository.save(new Ruta("M84", estaciones));

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
    void obtenerNombre() {
        browser.get(baseUrl + "/ruta/view/3");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nombreRutaId")));
        WebElement nombreRutaId = browser.findElement(By.id("nombreRutaId"));
        Assert.assertEquals("Nombre: M84", nombreRutaId.getText());
    }

}