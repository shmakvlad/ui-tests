package Run;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.sleep;

public class Wait {

    WebDriver driver = new ChromeDriver();

    @Test
    public void implicitlyWait(){
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.get("http://offers.staging.affise.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);

    // Selenide
        sleep(10000);
    }

    @Test
    public void explicitWait(){
    // Selenium
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("http://offers.staging.affise.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Authorization')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Authorization']")));

    // Login
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h3[text()='Authorization']")));
    }

}
