package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Conductor;
import com.example.sistematransmilenio.repository.ConductorRepository;
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

import java.time.Duration;
import java.util.List;

@ActiveProfiles("systemtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConductorSystemTest {

    private ChromeDriver browser;
    private WebDriverWait wait;

    private int timeout = 15;

    @Autowired
    ConductorRepository conductorRepository;

    String baseUrl;

    @BeforeEach
    void init() {
        conductorRepository.save(new Conductor("Sofia", 1000123456, 123456789, "Javeriana"));
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
        browser.get(baseUrl + "/conductor/list");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nombreConductorId")));
        List<WebElement> nombreConductorId = browser.findElements(By.id("nombreConductorId"));
        Assert.assertEquals("Sofia", nombreConductorId.get(0).getText());
    }

    @Test
    void obtenerCedula() {
        browser.get(baseUrl + "/conductor/list");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cedulaConductorId")));
        List<WebElement> cedulaConductorId = browser.findElements(By.id("cedulaConductorId"));
        Assert.assertEquals(1000123456, Integer.parseInt(cedulaConductorId.get(0).getText()));
    }

    @Test
    void obtenerTelefono() {
        browser.get(baseUrl + "/conductor/list");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("telefonoConductorId")));
        List<WebElement> telefonoConductorId = browser.findElements(By.id("telefonoConductorId"));
        Assert.assertEquals(123456789, Integer.parseInt(telefonoConductorId.get(0).getText()));
    }

    @Test
    void obtenerDireccion() {
        browser.get(baseUrl + "/conductor/list");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("direccionConductorId")));
        List<WebElement> direccionConductorId = browser.findElements(By.id("direccionConductorId"));
        Assert.assertEquals("Javeriana", direccionConductorId.get(0).getText());
    }

}