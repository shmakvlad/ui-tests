package Run;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.openqa.selenium.remote.BrowserType.CHROME;

public class SeleniumSelenide {

    private final Faker generate = new Faker();

    @RegisterExtension
    static ScreenShooterExtension sh = new ScreenShooterExtension(false);

    @BeforeAll
    public static void setUp() {
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    @Test
    public void connection(){
    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.get(Configuration.baseUrl);
        driver.navigate().to(Configuration.baseUrl);
    }

    @Test
    public void titleUrl(){
    // Selenide
        open("/user/login");
        title(); url(); titleUrl();

    // Selenium
        WebDriver driver = new ChromeDriver();
        driver.get(Configuration.baseUrl);
        driver.getCurrentUrl(); driver.getTitle();
    }

    @BeforeEach
    public void login(){
        open("/user/login");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $("button.btn.btn-success.btn-block").click();
        screenshot("login");
    }

}
