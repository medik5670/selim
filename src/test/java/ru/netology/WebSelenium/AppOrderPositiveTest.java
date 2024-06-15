package ru.netology.WebSelenium;

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

public class AppOrderPositiveTest {
    private WebDriver driver;

    @BeforeEach
    public void BeforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }
    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldBeSuccessfulForm() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Путров Петр Петрович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79995077768");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=order-success]")).isDisplayed());
    }

    @Test
    public void shouldBeSuccessfulNameWithDash() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ольга Дмитриевна-Палан");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79995077768");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=order-success]")).isDisplayed());
    }
}

