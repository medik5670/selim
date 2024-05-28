package ru.netology.WebSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.cssSelector;

public class AppOrderNegativeTest {
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
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void ShouldBeFailedIncorrectNameInput() {
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Aron");
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+76007650000");
        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class,'button')]")).click();
        assertEquals("Имя и Фамилия указаны неверно.Допустимы только русские буквы, пробелы и дефисы",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input_sub']"))
                        .getText().trim());

    }
}
