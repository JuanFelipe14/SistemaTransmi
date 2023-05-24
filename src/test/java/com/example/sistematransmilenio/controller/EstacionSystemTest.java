

package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Estacion;
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
import com.example.sistematransmilenio.repository.EstacionRepository;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("systemtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EstacionSystemTest {
    private ChromeDriver browser;
    private WebDriverWait wait;

    private int timeout = 5;

    @Autowired
    EstacionRepository estacionRepository;

    String baseUrl;

    @BeforeEach
    void init() {

        Estacion estacionTest = new Estacion("Alcala");

        estacionRepository.save(estacionTest);

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
        browser.get(baseUrl + "/estacion/list");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nombreEstacionId")));
        List<WebElement> nombreEstacionId = browser.findElements(By.id("nombreEstacionId"));
        Assert.assertEquals("Alcala", nombreEstacionId.get(0).getText());
    }

}





