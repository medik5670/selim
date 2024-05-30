package ru.netology.WebSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

public class AppOrderNegativeTest {
    public WebDriver driver;

    @BeforeEach
    public void BeforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
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
    public void shouldBeFailedIncorrectNameInput() {
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Aron");
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+76007650000");
        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class,'button')]")).click();
        assertEquals("Имя и Фамилия указаны неверно.Допустимы только русские буквы, пробелы и дефисы",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input__sub']"))
                        .getText().trim());
    }
    @Test
    public void shouldBeFailedIncorrectPhoneInput() {
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Ольга Павловна - Нитше");
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+7000000000");
        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class,'button')]")).click();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input__sub']"))
                        .getText().trim());
    }
    @Test
    public void shouldBeFailedDataNotEnteredEnough(){
        driver.findElement(cssSelector("[data-test-id = 'name']input")).sendKeys("Петров Петр-Петрович");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        assertEquals("Все поля обязательны для заполнения", text.trim());
    }
    @Test
    public void shouldBeFailedPhoneIncorrectNumbers(){
        driver.findElement(By.xpath("//span[@data-test-id='name']//input")).sendKeys("Иван Иванович Маслов");
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+2300000000");
        driver.findElement(By.xpath("//label[@data-test-id='agreement']")).click();
        driver.findElement(By.xpath("//button[contains(@class,'button')]")).click();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input__sub']"))
                        .getText().trim());
    }
    @Test
    public void shouldBeFailedDataNotEnteredInsufficient(){
        driver.findElement(By.xpath("//span[@data-test-id='phone']//input")).sendKeys("+79177113298");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        assertEquals("Все поля обязательны для заполнения", text.trim());
    }
    @Test
    public void shouldBePhoneIsNotCorrect()  {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванова Инна Валерьевна");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("Clear");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input__sub']"))
                        .getText().trim());
    }
    @Test
    public void shouldBeSufficientNumberDigits()  {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Дарья Петровна Липницкая");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+787656734215");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input__sub']"))
                        .getText().trim());
    }
    @Test
    public void shouldBeWithoutSpaces() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван Иванович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+ 79997097654");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например: +7 (901) 123-45-67",
                driver.findElement(By.xpath("//span[@data-test-id='name')[contains(@class,'input_invalid')]//span[@class='input__sub']"))
                        .getText().trim());
    }
    @Test
    void shouldBeFailedWithDash()  {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("-Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79177113298");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно", text);
    }
}





