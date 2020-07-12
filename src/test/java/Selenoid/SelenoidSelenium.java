package Selenoid;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.*;

//@Execution(ExecutionMode.CONCURRENT)  // Run in multiple thread
//@Execution(ExecutionMode.SAME_THREAD) // Run in one thread
public class SelenoidSelenium { // Clean Selenium

    private RemoteWebDriver driver;

    @BeforeEach
    public void setUpConfiguration() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void login1(){
        driver.get("http://offers.staging.affise.com");
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
    }

    @Test
    public void login2(){
        driver.get("http://offers.staging.affise.com");
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
    }

    @Test
    public void login3(){
        driver.get("http://offers.staging.affise.com");
        driver.findElement(byId("email")).sendKeys("ivan@gmail.com");
        driver.findElement(byId("password")).sendKeys("vlad12-8");
        driver.findElement(byXpath("//input[@id='sign']")).click();
        driver.findElement(byClassName("btn-success")).click();
    }

    @AfterEach
    public void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
