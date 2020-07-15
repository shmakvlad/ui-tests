package Selenoid;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

//@Execution(ExecutionMode.SAME_THREAD)   //         Run in one thread
//@Execution(ExecutionMode.CONCURRENT)    // Run in multiple thread
public class Selenoid {

    private RemoteWebDriver driver;
    private Faker generate = new Faker();

    @RegisterExtension
    static ScreenShooterExtension sh = new ScreenShooterExtension(false);


    // 1 Version | Before all | Option with SelenoidWebDriverProvider, the most stable option
    @BeforeAll
    public static void beforeAll1() {
        Configuration.browser = "Selenoid.Provider.SelenoidWebDriverProvider";
        Configuration.baseUrl = "http://offers.staging.affise.com";
    }

    // 2 Version | Before all | More stable option
    public static void beforeAll2() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "enableVNC",  true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("name", "Create Affiliate");
        capabilities.setCapability("timeZone", "Europe/Moscow");
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");

        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 6000;
        Configuration.remote= "http://localhost:4444/wd/hub";
        Configuration.browser = CHROME;
        Configuration.baseUrl = "http://offers.staging.affise.com";
        Configuration.browserSize = "1920x1080"; // Default size: 1366x768
    }

    // 3 Version | Before all | Different browsers type
    public void beforeAll3() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");
        capabilities.setCapability("enableVNC", true);

        driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Configuration.baseUrl = "http://offers.staging.affise.com";
        WebDriverRunner.setWebDriver(driver);
    }

    @Test()
    public void createAffiliate1() {
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

    @Test()
    public void createAffiliate2() {
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

    @Test()
    public void createAffiliate3() {
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

    @Test()
    public void createAffiliate4() {
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

    @Test()
    public void createAffiliate5() {
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

    @Test()
    public void createAffiliate6() {
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
        $("#email").setValue("root@user.admin");
        $("#password").setValue("-&z%5CZc7=V^U7AN");
        $(byXpath("//input[@id='sign']")).click();
        $("button.btn.btn-success.btn-block").click();
    }

    @AfterEach
    public void close(){
        WebDriverRunner.closeWebDriver();
    }

}
