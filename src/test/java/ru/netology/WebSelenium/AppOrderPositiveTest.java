package ru.netology.WebSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AppOrderPositiveTest {
    private WebDriver driver;

    @BeforeEach
    public void BeforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver.get("http://0.0.0.0:9999");
        driver = new ChromeDriver(options);
    }

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\nasty\\Downloads\\chromedriver-win64.zip\\chromedriver-win64\\chromedriver.exe\"");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void ShouldBeSuccessfulForm() {
        ChromeOptions options = new ChromeOptions();




    }
}
