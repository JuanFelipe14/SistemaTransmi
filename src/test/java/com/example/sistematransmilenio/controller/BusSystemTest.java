package com.example.sistematransmilenio.controller;

import com.example.sistematransmilenio.model.Bus;
import com.example.sistematransmilenio.repository.BusRepository;
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

@ActiveProfiles("systemtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BusSystemTest {

    private ChromeDriver browser;
    private WebDriverWait wait;

    private int timeout = 20;

    @Autowired
    BusRepository busRepository;

    String baseUrl;

    @BeforeEach
    void init() {
        busRepository.save(new Bus("HSA123", "2003"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
//options.addArguments("--headless");
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
    void obtenerPlaca() {
        browser.get(baseUrl + "/bus/view/1");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("placaId")));
        WebElement placaid = browser.findElement(By.id("placaId"));
        System.out.println(placaid.getText());
        Assert.assertEquals("Placa: HSA123", placaid.getText());
    }

    @Test
    void obtenerModelo() {
        browser.get(baseUrl + "/bus/view/1");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("modeloId")));
        WebElement modeloId = browser.findElement(By.id("modeloId"));
        Assert.assertEquals("Modelo: 2003", modeloId.getText());
    }

}