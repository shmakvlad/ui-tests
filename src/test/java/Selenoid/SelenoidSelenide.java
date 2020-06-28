package Selenoid;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelenoidSelenide {

    private RemoteWebDriver driver;
    private Faker generate = new Faker();

    @RegisterExtension
    static ScreenShooterExtension sh = new ScreenShooterExtension(false);

    // 1 Before all | Different browsers type
    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");
        capabilities.setCapability("enableVNC", true);

        Configuration.baseUrl = "http://offers.staging.affise.com";
        driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    // 2 Before all
    public void beforeAll() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "enableVNC",  true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("name", "Create Affiliate");
        capabilities.setCapability("timeZone", "Europe/Moscow");
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");

        Configuration.browserCapabilities = capabilities;
        Configuration.remote= "http://localhost:4444/wd/hub";
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
        Configuration.browserSize = "1920x1080"; // Default size: 1366x768
    }

    @Test
    public void createAffiliate() {
        open("/partners/new");
        $("#EditPartner_email").setValue(generate.internet().emailAddress());
        $("#EditPartner_password").setValue(generate.internet().password(6,12));
        $("#EditPartner_manager_id").selectOption(3);
        $("#EditPartner_country").selectOptionByValue("RU");
        $("#EditPartner_custom_fields_1").setValue(String.valueOf(generate.funnyName()));
        $("#EditPartner_status").selectOptionByValue("1");
        $("#EditPartner_submit").click();
        $("#EditPartner_submit").shouldHave(text("Save"));
        screenshot("Create Affiliate");
    }

    @BeforeEach
    public void login(){
        open("/user/login");
        $("#email").setValue("ivan@gmail.com");
        $("#password").setValue("vlad12-8");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
    }

    @AfterAll
    public void close(){
        WebDriverRunner.closeWebDriver();
    }

}
